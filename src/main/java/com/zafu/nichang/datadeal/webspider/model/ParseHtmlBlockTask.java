package com.zafu.nichang.datadeal.webspider.model;

import com.zafu.nichang.datadeal.webspider.util.OkHttpUtil;
import com.zafu.nichang.datadeal.webspider.util.RegUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * @author 倪畅
 * @version 1.0 2019-01-21
 *
 */
public class ParseHtmlBlockTask implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(ParseHtmlBlockTask.class);

    private String productUrl;

    /** 是否需要原始的cookie对象 */
    private String cookie;

    public ParseHtmlBlockTask() {
    }

    public ParseHtmlBlockTask(String productUrl, String cookie) {
        this.productUrl = productUrl;
        this.cookie = cookie;
    }

    @Override
    public void run() {
        try {
            String productHtml = OkHttpUtil.getHtmlByOkHttp(productUrl.replace("???", String.valueOf(1)), cookie);
            int pageCount = getMaxPage(productHtml);
            log.info("productUrl: {}， pageCount: {}", productUrl, pageCount);
            List<Product> productList = getProduct(pageCount);
            // 执行插入数据库
            // 到时候 直接 注入 xxxMapper 或者什么 service 来进行插入 就行 这里可以先别管
            // productMapper.save(productList);
            // 这里简单的输出
            System.out.println(productList.size());
            System.out.println(productList.stream().findFirst());
        } catch (Exception e) {
            log.error("【数据解析】发生异常：", e);
        }
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

    /**
     * 获得页面产品
     * 采用java8中{@link Function} 来重构模板方法 不同的地方已经抽象出来
     *
     * @param pageCount 不同的页面产品列表
     * @return
     * @throws Exception
     */
    private List<Product> getProduct(int pageCount) throws Exception {
        List<Product> productList = new LinkedList<>();
        // 替换为pageCount
        for (int i = 1; i < 10; i++) {
            String url = productUrl.replace("???", String.valueOf(i));
            String htmlPage = OkHttpUtil.getHtmlByOkHttp(url, cookie);
            log.info("解析网页成功！");
            List<String> currentPageHtmlBlocks = RegUtil.getRegInfoBlocks(Constant.OTA_WEB_HTML_BLOCK_REG_PATTERN, htmlPage);
            List<Product> currentPageProductList = currentPageHtmlBlocks.stream().map(this::getProduct).collect(toList());
            productList.addAll(currentPageProductList);
        }
        return productList;
    }

    private Product getProduct(String htmlBlock) {
        LinkedList<String> productNameLists = RegUtil.getRegInfoDetails(Constant.OTA_WEB_PRODUCT_REG_PATTERN, htmlBlock);
        LinkedList<String> productDetailsLists = RegUtil.getRegInfoDetails(Constant.OTA_WEB_DETAIL_REG_PATTERN, htmlBlock);
        LinkedList<String> productDateLists = RegUtil.getRegInfoDetails(Constant.OTA_WEB_DATE_REG_PATTERN, htmlBlock);
        return new Product(productNameLists, productDetailsLists, productDateLists);
    }
}
