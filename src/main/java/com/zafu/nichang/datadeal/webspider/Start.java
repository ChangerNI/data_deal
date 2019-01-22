package com.zafu.nichang.datadeal.webspider;

import com.zafu.nichang.datadeal.webspider.enums.ProductEnums;
import com.zafu.nichang.datadeal.webspider.model.ParseHtmlBlockTask;
import com.zafu.nichang.datadeal.webspider.model.PropertiesModel;
import com.zafu.nichang.datadeal.webspider.util.DbUtil;
import com.zafu.nichang.datadeal.webspider.util.PropertiesUtil;
import com.zafu.nichang.datadeal.webspider.util.RegUtil;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author 倪畅
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

    private static final Integer THREAD_COUNT = 5;

    public static ExecutorService htmlParserExecutorService = Executors.newFixedThreadPool(THREAD_COUNT,
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
            PropertiesModel pm = PropertiesUtil.getProperties("/properties_web.properties");
            logger.info("配置文件读取成功！");
            //连接源数据库
            conn = DbUtil.getConnection(pm.getDriver(), pm.getUrl(), pm.getUsername(), pm.getPassword());
            logger.info("数据库连接成功！");

            // 是否需要 Cookie对象？？
            String cookie = "";

            // 等待子线程结束
            CountDownLatch waiter = new CountDownLatch(THREAD_COUNT);

            // 开启多线程 执行
            for (ProductEnums productEnums : ProductEnums.values()) {
                ParseHtmlBlockTask parseHtmlBlockTask = new ParseHtmlBlockTask(productEnums, cookie, waiter);
                htmlParserExecutorService.submit(parseHtmlBlockTask);
            }

            waiter.await();
            logger.info("子线程结束！");
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
     * 获得产品属性列表
     * 弃用
     *
     * @param pattern
     * @param htmlBlocks
     * @return
     */
    @Deprecated
    private static LinkedList<String> mapToParamList(Pattern pattern, List<String> htmlBlocks) {
        return htmlBlocks.stream()
                .map(htmlBlock -> RegUtil.getRegInfoDetails(pattern, htmlBlock))
                .flatMap(Collection::stream)
                .collect(Collectors.toCollection(LinkedList::new));
    }





}