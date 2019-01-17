package com.zafu.nichang.datadeal.house.util;

import com.zafu.nichang.datadeal.house.model.HouseInfo;
import com.zafu.nichang.datadeal.house.model.HousePic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

/**
 * @author MountainTop
 * @version 1.0 2018-07-16
 */
public class DbUtil {
    /**
     * 日志
     */
    public static final Logger logger = LoggerFactory.getLogger(DbUtil.class);
    /**
     * 获取数据库连接
     *
     * @param driver   驱动
     * @param url      url
     * @param user     用户名
     * @param password 密码
     * @return
     */
    public static Connection getConnection(String driver, String url, String user, String password) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        return DriverManager.getConnection(url, user, password);
    }


    /**
     * 新增数据
     * @param conn
     * @param houseInfo
     * @throws Exception
     */
    public static void insertHouseInfo(Connection conn,HouseInfo houseInfo) throws  Exception{
        String sql="INSERT INTO `qz_spider`.`house_info` "+
                 "( `id`,`house_name`, `house_area`, `house_address`, `telephone`, `business_time`, `ota_type`, `create_time`, `update_time`) "+
                "VALUES "+
                "("+houseInfo.getId()+",'"+houseInfo.getHouseName()+"', '"+houseInfo.getHouseArea()+"', '"+houseInfo.getHouseAddress()+"', '"+houseInfo.getTelephone()+"', '"+houseInfo.getBusinessTime()+"', 8, NOW(), NOW())";
        logger.info(sql);
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.execute();
        List<HousePic> listPic=houseInfo.getHousePicList();
        if(listPic!=null){
            for(HousePic housePic:listPic){
                String sql2="INSERT INTO `qz_spider`.`house_pic` "+
                        "(`house_id`, `small_pic_url`, `big_pic_url`, `create_time`, `update_time`) VALUES "+
                        "("+housePic.getHouseId()+", '"+housePic.getSmallPicUrl()+"', '"+housePic.getBigPicUrl()+"', NOW(), NOW())";
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                ps2.execute();
            }
        }
    }
}
