package com.zafu.nichang.datadeal.webspider.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * @author 倪畅
 * @version 1.0 2019-01-16
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
     * 新增产品信息到数据库
     * @param conn
     * @param type
     * @param uniqueId
     * @param finalName
     * @param otaName
     * @param otaAddress
     * @throws Exception
     */
    public static void insertProductEverydayDetailsIntoDB(Connection conn,Integer type,String uniqueId,String finalName,Integer otaType,String otaName,String otaAddress) throws  Exception{
        String sql="INSERT INTO `qz_spider`.`name_mapping` ( `TYPE`, `UNIQUE_ID`, `FINAL_NAME`,`OTA_TYPE`, `OTA_NAME`, `OTA_ADDRESS`, `CREATE_TIME`, `UPDATE_TIME`) VALUES ("+type+",'"+uniqueId+"' ,'"+finalName+"',"+otaType+",'"+otaName+"', '"+otaAddress+"', NOW(), NOW())";
        PreparedStatement ps = conn.prepareStatement(sql.toString());
        ps.execute();
    }

}
