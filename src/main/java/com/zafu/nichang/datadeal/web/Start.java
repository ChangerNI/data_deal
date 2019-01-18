package com.zafu.nichang.datadeal.web;

import com.zafu.nichang.datadeal.web.model.Constant;
import com.zafu.nichang.datadeal.web.model.Product;
import com.zafu.nichang.datadeal.web.model.PropertiesModel;
import com.zafu.nichang.datadeal.web.util.DbUtil;
import com.zafu.nichang.datadeal.web.util.OkHttpUtil;
import com.zafu.nichang.datadeal.web.util.PropertiesUtil;
import com.zafu.nichang.datadeal.web.util.RegUtil;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @author nichang
 * @version 1.0 2019-01-16
 */
public class Start {
    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(Start.class);
    /**
     * ROOT_DIRECTORY
     * 根目录
     */
    private static final String ROOT_DIRECTORY = System.getProperty("user.dir");

    public static ExecutorService htmlParserExecutorService = Executors.newFixedThreadPool(5,
            new BasicThreadFactory.Builder().namingPattern("html-parser-%d").daemon(true).build());


    /**
     * 数据库连接
     */
    private static Connection conn = null;

    /**
     * 主方法  从程序入口
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) {
        try {
            //读取配置文件
            PropertiesModel pm = PropertiesUtil.getProperties(ROOT_DIRECTORY + "/config/propertires_web.properties");
            logger.info("配置文件读取成功！");
            //连接源数据库
            conn = DbUtil.getConnection(pm.getDriver(), pm.getUrl(), pm.getUsername(), pm.getPassword());
            logger.info("数据库连接成功！");
            int maxPageVegetable = getMaxPage(OkHttpUtil.getHtmlByOkHttp(pm.getWebVegetableUrl().replace("???", String.valueOf(1)), ""));
            int maxPageFruit = getMaxPage(OkHttpUtil.getHtmlByOkHttp(pm.getWebFruitUrl().replace("???", String.valueOf(1)), ""));
            int maxPageMeat = getMaxPage(OkHttpUtil.getHtmlByOkHttp(pm.getWebMeatUrl().replace("???", String.valueOf(1)), ""));
            int maxPageAquatic = getMaxPage(OkHttpUtil.getHtmlByOkHttp(pm.getWebAquaticUrl().replace("???", String.valueOf(1)), ""));
            int maxPageOil = getMaxPage(OkHttpUtil.getHtmlByOkHttp(pm.getWebOilUrl().replace("???", String.valueOf(1)), ""));

            logger.info("解析最大网页数成功！");
            List<Product> result = new LinkedList<>();

            logger.info("maxPageVegetable: {}, maxPageFruit: {}, maxPageMeat;{}, maxPageOil: {}",
                    maxPageVegetable, maxPageFruit, maxPageMeat, maxPageAquatic, maxPageOil);

            // 获得不同页面的产品列表
            // todo 开启多线程 此处你来写 用上面的htmlParserExecutorService 线程池
            // todo 测试的时候可以将count的这个字段随便写个值进去
            List<Product> product = getProduct(maxPageVegetable, PropertiesModel::getWebVegetableUrl, pm);
            // 提供一个思路
            // 线程采用Callable 然后for循环 再用下面的方法
            //htmlParserExecutorService.submit(callable)
            // 拿到返回值
            // 这里可以 每个线程都去数据库中插入了 就不用再交个主线程一起插入
            // 即 上面的result的这个列表就不要addAll了


            logger.info("所有产品列表：{}", result);

        } catch (Exception e) {
            logger.info("error：", e);
        } finally {
            try {
                //关闭数据库连接
                conn.close();
            } catch (Exception e) {
                logger.info(e.toString());
            }
        }
    }

    /**
     * 获得页面产品
     * 采用java8中{@link Function} 来重构模板方法 不同的地方已经抽象出来
     *
     * @param pageCount 不同的页面产品列表
     * @param getUrl 获得不同的页面的url的方法
     * @param propertiesModel propertiesModel
     * @return
     * @throws Exception
     */
    private static List<Product> getProduct(int pageCount, Function<PropertiesModel, String> getUrl,
                                            PropertiesModel propertiesModel) throws Exception {
        List<Product> productList = new LinkedList<>();
        for (int i = 1; i < pageCount; i++) {
            String url = getUrl.apply(propertiesModel).replace("???", String.valueOf(i));
            String htmlPage = OkHttpUtil.getHtmlByOkHttp(url, "");
            logger.info("解析网页成功！");
            List<String> currentPageHtmlBlocks = RegUtil.getRegInfoBlocks(Constant.OTA_WEB_HTML_BLOCK_REG_PATTERN, htmlPage);
            List<Product> currentPageProductList = currentPageHtmlBlocks.stream().map(Start::getProduct).collect(toList());
            productList.addAll(currentPageProductList);
        }
        return productList;
    }


    /**
     * 拼接三个产品特征LinkedList
     *
     * @param pattern
     * @param htmlBlocks
     * @return
     */
    private static LinkedList<String> mapToParamList(Pattern pattern, List<String> htmlBlocks) {
        return htmlBlocks.stream()
                .map(htmlBlock -> RegUtil.getRegInfoDetails(pattern, htmlBlock))
                .flatMap(Collection::stream)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    private static Product getProduct(String htmlBlock) {
        LinkedList<String> productNameLists = RegUtil.getRegInfoDetails(Constant.OTA_WEB_PRODUCT_REG_PATTERN, htmlBlock);
        LinkedList<String> productDetailsLists = RegUtil.getRegInfoDetails(Constant.OTA_WEB_DETAIL_REG_PATTERN, htmlBlock);
        LinkedList<String> productDateLists = RegUtil.getRegInfoDetails(Constant.OTA_WEB_DATE_REG_PATTERN, htmlBlock);
        return new Product(productNameLists, productDetailsLists, productDateLists);
    }

    /**
     * 获得网页最大页数
     *
     * @param html
     * @return
     */
    private static int getMaxPage(String html) {
        return RegUtil.getMaxPage(Constant.OTA_WEB_HTML_LAST_PAGE_REG_PATTERN, html);
    }

}