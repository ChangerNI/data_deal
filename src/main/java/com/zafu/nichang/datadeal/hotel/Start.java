package com.zafu.nichang.datadeal.hotel;
import com.zafu.nichang.datadeal.hotel.model.Constant;
import com.zafu.nichang.datadeal.hotel.model.PropertiesModel;
import com.zafu.nichang.datadeal.hotel.util.DbUtil;
import com.zafu.nichang.datadeal.hotel.util.OkHttpUtil;
import com.zafu.nichang.datadeal.hotel.util.PropertiesUtil;
import com.zafu.nichang.datadeal.hotel.util.RegUtil;
import com.zafu.nichang.datadeal.hotel.util.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * @author MountainTop
 * @version 1.0 2018-07-16
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

    private static Integer count=0;

    public static String[] cookies={
            "uuid=59b5cb088d734561897f.1532397263.1.0.0; _lx_utm=utm_source%3DBaidu%26utm_medium%3Dorganic; _lxsdk_cuid=164c9fea4586f-01c12d003d487e-7b1030-100200-164c9fea459c8; __mta=212619563.1532397266145.1532397266145.1532397266145.1; ci=189; rvct=189; __mta=212619563.1532397266145.1532397266145.1532397281008.2; _lxsdk_s=164c9fea459-0b-ea4-bf9%7C%7C7",
            "uuid=59b5cb088d734561897f.1532397263.1.0.0; _lx_utm=utm_source%3DBaidu%26utm_medium%3Dorganic; _lxsdk_cuid=164c9fea4586f-01c12d003d487e-7b1030-100200-164c9fea459c8; __mta=212619563.1532397266145.1532397266145.1532397266145.1; ci=189; rvct=189; __mta=212619563.1532397266145.1532397266145.1532397292857.2; _lxsdk_s=164c9fea459-0b-ea4-bf9%7C%7C10",
            "uuid=59b5cb088d734561897f.1532397263.1.0.0; _lx_utm=utm_source%3DBaidu%26utm_medium%3Dorganic; _lxsdk_cuid=164c9fea4586f-01c12d003d487e-7b1030-100200-164c9fea459c8; __mta=212619563.1532397266145.1532397266145.1532397266145.1; ci=189; rvct=189; __mta=212619563.1532397266145.1532397266145.1532397348037.2; _lxsdk_s=164c9fea459-0b-ea4-bf9%7C%7C13",
            "uuid=59b5cb088d734561897f.1532397263.1.0.0; _lx_utm=utm_source%3DBaidu%26utm_medium%3Dorganic; _lxsdk_cuid=164c9fea4586f-01c12d003d487e-7b1030-100200-164c9fea459c8; __mta=212619563.1532397266145.1532397266145.1532397266145.1; ci=189; rvct=189; __mta=212619563.1532397266145.1532397266145.1532397374082.2; _lxsdk_s=164c9fea459-0b-ea4-bf9%7C%7C16",
            "uuid=59b5cb088d734561897f.1532397263.1.0.0; _lx_utm=utm_source%3DBaidu%26utm_medium%3Dorganic; _lxsdk_cuid=164c9fea4586f-01c12d003d487e-7b1030-100200-164c9fea459c8; __mta=212619563.1532397266145.1532397266145.1532397266145.1; ci=189; rvct=189; __mta=212619563.1532397266145.1532397266145.1532397398940.2; _lxsdk_s=164c9fea459-0b-ea4-bf9%7C%7C19",
            "uuid=59b5cb088d734561897f.1532397263.1.0.0; _lx_utm=utm_source%3DBaidu%26utm_medium%3Dorganic; _lxsdk_cuid=164c9fea4586f-01c12d003d487e-7b1030-100200-164c9fea459c8; __mta=212619563.1532397266145.1532397266145.1532397266145.1; ci=189; rvct=189; __mta=212619563.1532397266145.1532397266145.1532397417593.2; _lxsdk_s=164c9fea459-0b-ea4-bf9%7C%7C22",
            "uuid=59b5cb088d734561897f.1532397263.1.0.0; _lx_utm=utm_source%3DBaidu%26utm_medium%3Dorganic; _lxsdk_cuid=164c9fea4586f-01c12d003d487e-7b1030-100200-164c9fea459c8; __mta=212619563.1532397266145.1532397266145.1532397266145.1; ci=189; rvct=189; __mta=212619563.1532397266145.1532397266145.1532397440219.2; _lxsdk_s=164c9fea459-0b-ea4-bf9%7C%7C25",
            "uuid=59b5cb088d734561897f.1532397263.1.0.0; _lx_utm=utm_source%3DBaidu%26utm_medium%3Dorganic; _lxsdk_cuid=164c9fea4586f-01c12d003d487e-7b1030-100200-164c9fea459c8; __mta=212619563.1532397266145.1532397266145.1532397266145.1; ci=189; rvct=189; __mta=212619563.1532397266145.1532397266145.1532397440219.2; _lxsdk_s=164c9fea459-0b-ea4-bf9%7C%7C28"
    };
    /**
     * 主方法  从程序入口
     * @param args
     * @throws Exception
     */
      public static void main(String [] args){
          try {
              String html1 = OkHttpUtil.getHtmlByOkHttp4("http://www.xinfadi.com.cn/" + java.net.URLEncoder.encode("", "utf-8"), "");
              logger.info(html1);
//              //读取配置文件
//              PropertiesModel pm = PropertiesUtil.getProperties(ROOT_DIRECTORY + "/config/propertires_hotel.properties");
//              logger.info("配置文件读取成功！");
//              //连接源数据库
//              conn = DbUtil.getConnection(pm.getDriver(), pm.getUrl(), pm.getUsername(), pm.getPassword());
//              logger.info("数据库连接成功！");
//              //查询基础数据
//              List<Map<String, Object>> listMap = DbUtil.findHotelInfo(conn);
//              logger.info("基础数据查询成功！总共" + listMap.size() + "条记录");
//              for (int i = 0; i < listMap.size(); i++) {
//                  String uniqueId = listMap.get(i).get("UNIQUE_ID").toString();
//                  String name = listMap.get(i).get("NAME").toString();
//                  //遍历九个网站
//                  for (int j = 1; j < Constant.CONSTANT_INTEGER_TEN; j++) {
//                      switch (j) {
//                          case 1:
//                              //携程
//                              try {
//
//                                  String html1 = OkHttpUtil.getHtmlByOkHttp4(pm.getOtaWeb1Url() + java.net.URLEncoder.encode(name, "utf-8"), "id=2428057cb13cbbcc||t=1532412021|et=730|cs=002213fd489391d4aee7d624eb");
//                                  logger.info(name);
//                                  String otaName = RegUtil.getRegInfo(Constant.OTA_WEB_1_REG_3, html1);
//                                  String totalHits = RegUtil.getRegInfo(Constant.OTA_WEB_1_REG_2,html1);
//                                  String otaAddress = RegUtil.getRegInfo(Constant.OTA_WEB_1_REG_4, html1);
//                                  boolean noReg = RegUtil.isFindHotel(Constant.OTA_WEB_1_REG_5, html1);
//                                 // if (!noReg) {
//                                          if(totalHits.trim().equals("1")){
//                                              logger.info("携程：第" + (i + 1) + "条记录,名称：" + otaName + "，地址:" + otaAddress);
//                                              DbUtil.insertNameMapping(conn, 4, uniqueId, name, j, otaName, otaAddress);
//                                              logger.info("携程：数据存储成功！");
//                                          }else{
//                                              logger.info("携程：模糊数据！");
//                                          }
//
//                              }catch (Exception e){
//                                  logger.info("携程：没有找到数据！");
//                              }
//                              break;
//                          case 2:
//                              break;
//                          case 3:
////                              if(true){
////                                  break;
////                              }
//                              //同程旅游
////                              try{
////                                  String html3= OkHttpUtil.getHtmlByOkHttp(pm.getOtaWeb3Url().replace("??", java.net.URLEncoder.encode(name, "utf-8")),"");
////                                  if(RegUtil.isFindHotel(Constant.OTA_WEB_3_REG_1,html3)){
////                                        String otaName=RegUtil.getRegInfo(Constant.OTA_WEB_3_REG_2,html3);
////                                        String otaAddress=RegUtil.getRegInfo(Constant.OTA_WEB_3_REG_3,html3);
////                                        otaAddress=otaAddress!=null?StringUtil.deleteHtmlContent3(otaAddress):otaAddress;
////                                      if(otaName!=null&&otaAddress!=null){
////                                          logger.info("同程：第" + (i + 1) + "条记录,名称：" + otaName + "，地址:" + otaAddress);
////                                          DbUtil.insertNameMapping(conn, 4, uniqueId, name, j, otaName, otaAddress);
////                                          logger.info("同程：数据存储成功！");
////                                      }
////                                  }else{
////                                      logger.info("同程：查询到同类型的很多！");
////                                  }
////                              }catch (Exception e){
////                                  logger.info("同程：查询失败");
////                              }
//                              break;
//                          case 4:
////                              if(true){
////                                  break;
////                              }
//                              //驴妈妈
////                              String webUrl4 = pm.getOtaWeb4Url().replace("??", java.net.URLEncoder.encode(name, "utf-8"));
////                              Document doc4= Jsoup.parse(new URL(webUrl4),10000);
////                              String html4=doc4!=null?doc4.toString():null;
////                              if(html4!=null){
////                                  String html5 = RegUtil.getRegInfo(Constant.OTA_WEB_4_REG_1, html4);
////                                  if(!RegUtil.isFindHotel(Constant.OTA_WEB_4_REG_4,html5)){
////                                      String otaName = RegUtil.getRegInfo(Constant.OTA_WEB_4_REG_2, html5);
////                                      String otaAddress = RegUtil.getRegInfo(Constant.OTA_WEB_4_REG_3, html5)!=null?RegUtil.getRegInfo(Constant.OTA_WEB_4_REG_3, html5):"";
////                                      otaAddress=otaAddress.replace("<i>","").replace("</i>","").trim();
////                                      logger.info("驴妈妈：第" + (i+1) + "条记录,名称：" + otaName + "，地址:" + otaAddress);
////                                      DbUtil.insertNameMapping(conn, 4, uniqueId, name, j, otaName, otaAddress);
////                                      logger.info("驴妈妈：数据存储成功！");
////                                  }else{
////                                      logger.info("驴妈妈：没有查询到相关信息！");
////                                  }
////                              }else{
////                                  logger.info("驴妈妈：未获取html内容");
////                              }
//                              break;
//                          case 5:
////                              if(true){
////                                  break;
////                              }
//                              //途牛
////                              try{
////                                  String webUrl5 = pm.getOtaWeb5Url().replace("???", DateUtil.getTomorrowDate(1))
////                                          .replace("????",DateUtil.getTomorrowDate(2))
////                                          .replace("??", java.net.URLEncoder.encode(name, "utf-8"));
////                                  CloseableHttpClient httpCilent = HttpClients.createDefault();
////                                  HttpGet httpGet = new HttpGet(webUrl5);
////                                  HttpResponse httpResponse = httpCilent.execute(httpGet);
////                                  if(httpResponse.getStatusLine().getStatusCode() == 200){
////                                      String srtResult = EntityUtils.toString(httpResponse.getEntity());
////                                      Map<String,Object> resultMap= JSONObject.fromObject(srtResult);
////                                      Map<String,Object> mapData=JSONObject.fromObject(resultMap.get("data")) ;
////                                      JSONArray mapList= JSONArray.fromObject(mapData.get("list"));
////                                      Map<String,Object> page=JSONObject.fromObject(mapData.get("page"));
////                                      String total=page.get("total").toString();
////                                      if(total.equals(Constant.CONSTANT_STRING_ONE)){
////                                          Map<String,Object>  mapResult= JSONObject.fromObject(mapList.get(0));
////                                          String otaName=mapResult.get("name").toString();
////                                          String otaAddress=mapResult.get("address").toString();
////                                          logger.info("途牛：第" + (i+1) + "条记录,名称：" + otaName + "，地址:" + otaAddress);
////                                          DbUtil.insertNameMapping(conn, 4, uniqueId, name, j, otaName, otaAddress);
////                                          logger.info("途牛：数据存储成功！");
////                                      }else{
////                                          logger.info("途牛：没有查询到相关记录！");
////                                      }
////                                  }else{
////                                      logger.info("途牛：网页获取失败："+httpResponse.getStatusLine().getStatusCode());
////                                  }
////                              }catch (Exception e){
////                                    logger.info("途牛：没有查询到记录！");
////                              }
//                              break;
//                          case 6:
//                              break;
//                          case 7:
//                              break;
//                          case 8:
////                              if(true){
////                                  break;
////                              }
//                              //大众
////                              try {
////                                  String html8 = OkHttpUtil.getHtmlByOkHttp(pm.getOtaWeb8Url() + java.net.URLEncoder.encode(name, "utf-8"), "_lxsdk_cuid=164a0a8329ac8-0a5d285a3bed5-737356c-100200-164a0a8329bc8; _lxsdk=164a0a8329ac8-0a5d285a3bed5-737356c-100200-164a0a8329bc8; _hc.v=f6ea0ea1-f5f6-4244-13f1-9115d93ff01c.1531703735; s_ViewType=10; aburl=1; cityInfo=%7B%22cityId%22%3A106%2C%22cityEnName%22%3A%22quzhou%22%2C%22cityName%22%3A%22%E8%A1%A2%E5%B7%9E%22%7D; __mta=142448525.1531907158646.1531907158646.1531907163524.2; _thirdu.c=c3af73640d357318e7fd0a83d7291deb; ua=%E6%97%B6%E9%97%B4%E7%85%AE%E6%B0%B4_7265; ctu=34325c057e74cd168ff49e71c7baec8302c2d4be9b9516b29cfbfa9d87e9304b; cy=106; cye=quzhou; _dp.ac.v=20eeb49b-b87c-4158-b999-122d81c3df81; JSESSIONID=0A164343295088358F3217B9B8EDE0FB; thirdtoken=7F6881E83655A458902AB0B15679BF47; ctu=29ce8941316e393ed415b40c6ae1e252bcc9b224b3fb29dd716cb6b72ff473b6; _lxsdk_s=164b12f8db9-cde-fb8-d9%7C%7C297");
////                                  logger.info(name);
////                                  String otaName = RegUtil.getRegInfo(Constant.OTA_WEB_8_REG_1, html8);
////                                  String otaRegion = RegUtil.getRegInfo(Constant.OTA_WEB_8_REG_2, html8);
////                                  String shopId = RegUtil.getRegInfo(Constant.OTA_WEB_8_REG_3, html8);
////                                  String totalHits = RegUtil.getRegInfo(Constant.OTA_WEB_8_REG_4, html8);
////                                  if (totalHits.equals("1") && otaName != null && otaRegion != null) {
////                                      String html82 = OkHttpUtil.getHtmlByOkHttp(pm.getOtaWeb8Url2() + shopId, "_lxsdk_cuid=164a0a8329ac8-0a5d285a3bed5-737356c-100200-164a0a8329bc8; _lxsdk=164a0a8329ac8-0a5d285a3bed5-737356c-100200-164a0a8329bc8; _hc.v=f6ea0ea1-f5f6-4244-13f1-9115d93ff01c.1531703735; s_ViewType=10; aburl=1; cityInfo=%7B%22cityId%22%3A106%2C%22cityEnName%22%3A%22quzhou%22%2C%22cityName%22%3A%22%E8%A1%A2%E5%B7%9E%22%7D; __mta=142448525.1531907158646.1531907158646.1531907163524.2; _thirdu.c=c3af73640d357318e7fd0a83d7291deb; ua=%E6%97%B6%E9%97%B4%E7%85%AE%E6%B0%B4_7265; ctu=34325c057e74cd168ff49e71c7baec8302c2d4be9b9516b29cfbfa9d87e9304b; cy=106; cye=quzhou; _dp.ac.v=20eeb49b-b87c-4158-b999-122d81c3df81; JSESSIONID=0A164343295088358F3217B9B8EDE0FB; thirdtoken=7F6881E83655A458902AB0B15679BF47; ctu=29ce8941316e393ed415b40c6ae1e252bcc9b224b3fb29dd716cb6b72ff473b6; _lxsdk_s=164b12f8db9-cde-fb8-d9%7C%7C297");
////                                      String otaAddress = RegUtil.getRegInfo(Constant.OTA_WEB_8_REG_5, html82);
////                                      logger.info("大众：第" + (i + 1) + "条记录,名称：" + otaName + "，地址:" + otaAddress);
////                                      DbUtil.insertNameMapping(conn, 4, uniqueId, name, j, otaName, otaAddress);
////                                      logger.info("大众：数据存储成功！");
////                                  } else {
////                                      logger.info("大众：没有找到数据！");
////                                  }
////                              }catch (Exception e){
////                                  logger.info("大众：没有找到数据！");
////                              }
//                              break;
//                          case 9:
//                              //美团
////                              try {
////                                  int num = 1000+(int)(Math.random()*5000);
////                                  //Thread.sleep(num);
////                                  int N = (int)(Math.random()*7);
////                                  String html9 = OkHttpUtil.getHtmlByOkHttp4(pm.getOtaWeb9Url() + java.net.URLEncoder.encode(name, "utf-8"), "uuid=0e24eea7e5204a469f6b.1532402426.1.0.0; _lx_utm=utm_source%3DBaidu%26utm_medium%3Dorganic; _lxsdk_cuid=164ca4d6b54c8-07614d89853c92-7b1030-100200-164ca4d6b54c8; __mta=50118297.1532402428938.1532402428938.1532402428938.1; ci=189; rvct=189; __mta=50118297.1532402428938.1532402428938.1532402435917.2; _lxsdk_s=164ca4d6b54-dca-45d-07c%7C%7C4");
////                                  logger.info(name);
////                                  String otaName = RegUtil.getRegInfo(Constant.OTA_WEB_9_REG_2, html9);
////                                  String totalHits = RegUtil.getRegInfo(Constant.OTA_WEB_9_REG_5,html9);
////                                  String otaAddress = RegUtil.getRegInfo(Constant.OTA_WEB_9_REG_3, html9);
////                                  boolean noReg = RegUtil.isFindHotel(Constant.OTA_WEB_9_REG_4, html9);
////                                  boolean errorMsg = RegUtil.isFindHotel(Constant.OTA_WEB_9_REG_6, html9);
////                                  if (!noReg) {
////                                      if(!errorMsg){
////                                          if(totalHits != null){
////                                              logger.info("美团：第" + (i + 1) + "条记录,名称：" + otaName + "，地址:" + otaAddress);
////                                              DbUtil.insertNameMapping(conn, 4, uniqueId, name, j, otaName, otaAddress);
////                                              logger.info("美团：数据存储成功！");
////                                          }else{
////                                              logger.info("美团：模糊数据！");
////                                          }
////                                      }else {
////                                          logger.info("美团：服务器拒绝请求!");
////                                      }
////
////                                  } else {
////                                      logger.info("美团：没有找到数据！");
////                                  }
////                              }catch (Exception e){
////                                  logger.info("美团：没有找到数据！");
////                              }
//                              break;
//                          case 10:
//                              break;
//                          case 11:
////                              if(true){
////                                  break;
////                              }
//                              //飞猪
////                              try{
////                                  String html11=OkHttpUtil.getHtmlByOkHttp(pm.getOtaWeb11Url()+java.net.URLEncoder.encode(name, "utf-8"),"cna=Rl2JE94Ptj4CAT2kKCJkOPvC; UM_distinctid=164accba2a1423-0bcfcc03f95585-737356c-100200-164accba2a22b; hng=\"\"; uss=\"\"; t=73113c9e60dc9107557b49d4a0bc24a1; tracknick=hushanfeng520; lid=hushanfeng520; _tb_token_=ee38560a3076f; cookie2=1c1649cfd5314dc5818145d85f08cd2a; chanelStat=\"NA==\"; chanelStatExpire=\"2018-07-23 08:56:29\"; CNZZDATA1253581663=58810888-1531902550-https%253A%252F%252Fwww.fliggy.com%252F%7C1532054546; l=aBvZvwkOHEQ-3nLBsMa4uskHTVCxS3ZH2tsy1MazATqm0OHop3Ic4jno-VwR7_qC55Py_K-59; uc1=cookie16=URm48syIJ1yk0MX2J7mAAEhTuw%3D%3D&cookie21=W5iHLLyFfXVRDP8mxoRA8A%3D%3D&cookie15=URm48syIIVrSKA%3D%3D&existShop=false&pas=0&cookie14=UoTfKfcG68XUGg%3D%3D&tag=8&lng=zh_CN; uc3=vt3=F8dBzrhAK4Y1TfvBsxo%3D&id2=UU26%2BwzNBpFFMg%3D%3D&nk2=CzhefJna7nub2yvNhA%3D%3D&lg2=UtASsssmOIJ0bQ%3D%3D; _l_g_=Ug%3D%3D; ck1=\"\"; unb=2587445316; lgc=hushanfeng520; cookie1=B0TxaOEQj4qJwO4fRJyJg6bnf1WhBmVHAkVaQZ2nTfo%3D; login=true; cookie17=UU26%2BwzNBpFFMg%3D%3D; _nk_=hushanfeng520; csg=4d0fb4b7; skt=71bb0335add6f321; JSESSIONID=8BCAEB53E1E3EFDBF10CD13024EEB01D; isg=BBwcqI4Gq369UF-8quO3iq6w7TrExcoZfQRQuPYdMofqQbzLHqWQT5LzpekcUvgX");
////                                  if(!RegUtil.isFindHotel(Constant.OTA_WEB_11_REG_1,html11)){
////                                      String otaName=RegUtil.getRegInfo(Constant.OTA_WEB_11_REG_2,html11);
////                                      String otaAddress=RegUtil.getRegInfo(Constant.OTA_WEB_11_REG_3,html11);
////                                      if(otaName!=null&&otaAddress!=null){
////                                          logger.info("飞猪：第" + (i + 1) + "条记录,名称：" + otaName + "，地址:" + otaAddress);
////                                          DbUtil.insertNameMapping(conn, 4, uniqueId, name, j, otaName, otaAddress);
////                                          logger.info("飞猪：数据存储成功！");
////                                      }
////                                  }else{
////                                      logger.info("飞猪：抱歉，没有找到数据！");
////                                  }
////                                  count++;
////                                  if(count%50==0){
////                                      //执行五十条就睡眠十五分钟
////                                      logger.info("睡眠五分钟开始");
////                                      Thread.sleep(300000);
////                                      logger.info("睡眠五分钟结束");
////                                  }
////                              }catch (Exception e){
////                                  logger.info("飞猪：没有找到数据！");
////                              }
//                              break;
//                          default:
//
//                      }
//                  }
//                  //break;
//              }
          }catch (Exception e){
                logger.info(e.toString());
          }finally {
              try{
                  //关闭连接
                  conn.close();
              }catch (Exception e){
                  logger.info(e.toString());
              }
          }
      }
}