package com.zafu.nichang.datadeal.web;

import com.zafu.nichang.datadeal.web.model.Constant;
import com.zafu.nichang.datadeal.web.model.Product;
import com.zafu.nichang.datadeal.web.model.PropertiesModel;
import com.zafu.nichang.datadeal.web.util.DbUtil;
import com.zafu.nichang.datadeal.web.util.OkHttpUtil;
import com.zafu.nichang.datadeal.web.util.PropertiesUtil;
import com.zafu.nichang.datadeal.web.util.RegUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
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

    /**
     * 数据库连接
     */
    private static Connection conn=null;

    /**
     * 主方法  从程序入口
     * @param args
     * @throws Exception
     */
      public static void main(String [] args){
          try {
              //读取配置文件
              PropertiesModel pm = PropertiesUtil.getProperties(ROOT_DIRECTORY + "/config/propertires_web.properties");
              logger.info("配置文件读取成功！");
              //连接源数据库
              conn = DbUtil.getConnection(pm.getDriver(), pm.getUrl(), pm.getUsername(), pm.getPassword());
              logger.info("数据库连接成功！");
              String htmlIndex = OkHttpUtil.getHtmlByOkHttp(pm.getWebVegetableUrl().replace("???", String.valueOf(1)), "");
              int maxPage = RegUtil.getMaxPage(Constant.OTA_WEB_HTML_LAST_PAGE_REG_PATTERN, htmlIndex);
              logger.info("解析最大网页数成功！");
              System.out.println(maxPage);
              List<Product> productList = new LinkedList<>();
              for(int i=1; i<10; i++) {
                  String url = pm.getWebVegetableUrl().replace("???", String.valueOf(i));
                  String htmlPage = OkHttpUtil.getHtmlByOkHttp(url, "");
                  logger.info("解析网页成功！");
                  List<String> htmlBlocks = RegUtil.getRegInfoBlocks(Constant.OTA_WEB_HTML_BLOCK_REG_PATTERN, htmlPage);
                  LinkedList<String> productNameLists = mapToParamList(Constant.OTA_WEB_PRODUCT_REG_PATTERN, htmlBlocks);

                  LinkedList<String> productDetailsLists = mapToParamList(Constant.OTA_WEB_DETAIL_REG_PATTERN, htmlBlocks);

                  LinkedList<String> productDateLists = mapToParamList(Constant.OTA_WEB_DATE_REG_PATTERN, htmlBlocks);

                  Product product = new Product(productNameLists, productDetailsLists, productDateLists);
                  productList.add(product);
              }
              System.out.println(productList);
          }catch (Exception e){
                logger.info("error：", e);
          }finally {
              try{
                  //关闭数据库连接
                  conn.close();
              }catch (Exception e){
                  logger.info(e.toString());
              }
          }
      }

    private static LinkedList<String> mapToParamList(Pattern pattern, List<String> htmlBlocks) {
        return htmlBlocks.stream()
                .map(htmlBlock -> RegUtil.getRegInfoDetails(pattern, htmlBlock))
                .flatMap(Collection::stream)
                .collect(Collectors.toCollection(LinkedList::new));
    }


}