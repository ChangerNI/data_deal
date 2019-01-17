package com.zafu.nichang.datadeal.hotel.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
     * 查询酒店数据
     * @param conn
     * @return
     * @throws Exception
     */
    public static List<Map<String, Object>> findHotelInfo(Connection conn) throws  Exception{
        List<Map<String, Object>> result=new ArrayList<Map<String, Object>>();
        String sql="select * from z_hotel_info";
        PreparedStatement ps = conn.prepareStatement(sql.toString());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Map<String,Object> map=new HashMap<String, Object>(16);
            map.put("UNIQUE_ID",rs.getString("UNIQUE_ID"));
            map.put("NAME",rs.getString("NAME"));
            result.add(map);
        }
        return result;
    }
    /**
     * 新增到数据库
     * @param conn
     * @param type
     * @param uniqueId
     * @param finalName
     * @param otaName
     * @param otaAddress
     * @throws Exception
     */
    public static void insertNameMapping(Connection conn,Integer type,String uniqueId,String finalName,Integer otaType,String otaName,String otaAddress) throws  Exception{
        String sql="INSERT INTO `qz_spider`.`name_mapping` ( `TYPE`, `UNIQUE_ID`, `FINAL_NAME`,`OTA_TYPE`, `OTA_NAME`, `OTA_ADDRESS`, `CREATE_TIME`, `UPDATE_TIME`) VALUES ("+type+",'"+uniqueId+"' ,'"+finalName+"',"+otaType+",'"+otaName+"', '"+otaAddress+"', NOW(), NOW())";
        PreparedStatement ps = conn.prepareStatement(sql.toString());
        ps.execute();
    }

}
