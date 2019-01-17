package com.zafu.nichang.datadeal.house;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zafu.nichang.datadeal.house.util.DbUtil;
import com.zafu.nichang.datadeal.house.util.PropertiesUtil;
import com.zafu.nichang.datadeal.house.model.Constant;
import com.zafu.nichang.datadeal.house.model.HouseInfo;
import com.zafu.nichang.datadeal.house.model.HousePic;
import com.zafu.nichang.datadeal.house.model.PropertiesModel;
import com.zafu.nichang.datadeal.house.util.OkHttpUtil;
import com.zafu.nichang.datadeal.house.util.RegUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
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
     * 程序入口
     * @param args
     */
    public static void main(String [] args){
        try{
            //读取配置文件
            PropertiesModel pm = PropertiesUtil.getProperties(ROOT_DIRECTORY + "/config/propertires_house.properties");
            logger.info("配置文件读取成功！");
            //连接源数据库
            conn = DbUtil.getConnection(pm.getDriver(), pm.getUrl(), pm.getUsername(), pm.getPassword());
            logger.info("数据库连接成功！");
            String html= OkHttpUtil.getHtmlByOkHttp(pm.getDazhongurl(),pm.getCookie());
            if(html!=null){
                /**思路：
                 * 1、先获取总共页数
                 * 2、然后获取每一页的部分基础数据
                 * 3、进一步获取详情页数据包括图片
                 */
                String htmlPage= RegUtil.getRegInfo(Constant.DAZHONG_REG_2,html);
                Integer pageSize=RegUtil.getPageNum(Constant.DAZHONG_REG_3,htmlPage);
                logger.info("总共有"+pageSize+"页数据！");
                for (int i=1;i<=pageSize;i++){
                    logger.info("第"+i+"页数据：");
                    String html2=OkHttpUtil.getHtmlByOkHttp(pm.getDazhongurl()+"p"+i,pm.getCookie());
                    if(html2!=null){
                        String htmlList=RegUtil.getRegInfo(Constant.DAZHONG_REG_1,html2);
                        List<HouseInfo> listHouse=analysisHtmlList(htmlList);
                        for(int j=0;j<listHouse.size();j++){
                            //根据ID 查询详情页面
                            String htmlDetail=OkHttpUtil.getHtmlByOkHttp(pm.getDazhongurldetal()+listHouse.get(j).getId(),pm.getCookie());
                            if(htmlDetail!=null){
                                //处理详情页面  截图有用部分
                                String htmlDetail2=RegUtil.getRegInfo(Constant.DAZHONG_REG_9,htmlDetail);
                                HouseInfo houseInfo=analysisHtmlList(listHouse.get(j),htmlDetail2);
                                //获取图片数据
                                String responseBody=OkHttpUtil.getHtmlByOkHttp(pm.getDazhongimgurl()+houseInfo.getId(),pm.getCookie());
                                if(responseBody!=null){
                                    logger.info(responseBody);
                                    try{
                                        JSONObject mapPic= JSONObject.fromObject(responseBody.trim());
                                        JSONArray mapPicArray=JSONArray.fromObject(mapPic.get("picsShopPic"));
                                        List<HousePic> listPic=new ArrayList<HousePic>();
                                        for (int n=0;n<mapPicArray.size();n++){
                                            JSONObject mapPicN=JSONObject.fromObject(mapPicArray.get(n));
                                            HousePic housePic=new HousePic();
                                            housePic.setHouseId(houseInfo.getId());
                                            housePic.setSmallPicUrl(mapPicN.get("smallPicture").toString());
                                            housePic.setBigPicUrl(mapPicN.get("bigPicture").toString());
                                            listPic.add(housePic);
                                        }
                                        houseInfo.setHousePicList(listPic);
                                    }catch (Exception e){
                                        logger.info("没有图片！");
                                    }
                                }
                                logger.info(houseInfo.toString());
                                //存储基础数据
                                DbUtil.insertHouseInfo(conn,houseInfo);
                                logger.info("存储数据成功！");
                            }else{
                                logger.info("第"+i+"页数据,第"+(j+1)+"条记录，详情页未获取成功！");
                            }
                        }
                    }else{
                        logger.info("第"+i+"页数据未获取成功！");
                    }
                    break;
                }
            }else{
                logger.info("未能抓取html信息！");
            }
        }catch (Exception e){
            logger.info(e.getMessage());
        }
    }


    /**
     * 分析html 获取基础数据
     * @param html
     * @return
     */
    public static List<HouseInfo> analysisHtmlList(String html){
        List<HouseInfo> listHouse=new ArrayList<HouseInfo>();
        List<String> ids=new ArrayList<String>();
        List<String> houseNames=new ArrayList<String>();
        List<String> houseAreas=new ArrayList<String>();
        List<String> houseAddress=new ArrayList<String>();
        /**
         * 名称
         */
        Pattern p4 = Pattern.compile(Constant.DAZHONG_REG_4);
        Matcher m4 = p4.matcher(html);
        while(m4.find()){
            houseNames.add(m4.group(2));
        }
        /**
         * 区域  4
         */
        Pattern p6 = Pattern.compile(Constant.DAZHONG_REG_6);
        Matcher m6 = p6.matcher(html);
        while(m6.find()){
            houseAreas.add(m6.group(4));
        }
        /**
         * 地址  2
         */
        Pattern p7 = Pattern.compile(Constant.DAZHONG_REG_7);
        Matcher m7 = p7.matcher(html);
        while(m7.find()){
            houseAddress.add(m7.group(2));
        }

        /**
         * ID
         */
        Pattern p8 = Pattern.compile(Constant.DAZHONG_REG_8);
        Matcher m8 = p8.matcher(html);
        while(m8.find()){
            ids.add(m8.group(2));
        }

        for (int i=0;i<houseNames.size();i++){
            HouseInfo houseInfo=new HouseInfo();
            houseInfo.setId(ids.get(i));
            houseInfo.setHouseName(houseNames.get(i));
            houseInfo.setHouseArea(houseAreas.get(i));
            houseInfo.setHouseAddress(houseAddress.get(i));
            listHouse.add(houseInfo);
        }
        return listHouse;
    }

    /**
     * 进一步获取  电话和营业时间
     * @param houseInfo
     * @param html
     * @return
     */
    public static HouseInfo analysisHtmlList(HouseInfo houseInfo,String html){
        /**
         * 电话
         */
        Pattern p10 = Pattern.compile(Constant.DAZHONG_REG_10);
        Matcher m10 = p10.matcher(html);
        while(m10.find()){
            String telephone=m10.group(2)!=null?m10.group(2).trim():"";
            houseInfo.setTelephone(telephone);
        }
        /**
         * 营业时间
         */
        Pattern p11 = Pattern.compile(Constant.DAZHONG_REG_11);
        Matcher m11 = p11.matcher(html);
        while(m11.find()){
            houseInfo.setBusinessTime(m11.group(2).trim());
        }
        return houseInfo;
    }
}
