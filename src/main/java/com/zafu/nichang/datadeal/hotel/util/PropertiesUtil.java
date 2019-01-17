package com.zafu.nichang.datadeal.hotel.util;

import com.zafu.nichang.datadeal.hotel.model.PropertiesModel;

import java.io.*;
import java.util.*;

/**
 * 读取配置文件工具类
 *
 * @author MountainTop
 * @version 1.0 2018-07-16
 */
public class PropertiesUtil {
    /**
     * 配置文件对象
     */
    private static Properties props = null;

    /**
     * 获取所有配置信息
     *
     * @return
     * @throws Exception
     */
    public static PropertiesModel getProperties(String filename) throws Exception {
        PropertiesModel pm = new PropertiesModel();
        Map<String, String> propertiesMap = PropertiesUtil.readAllProperties(filename);
        pm.setDriver(propertiesMap.get("driver"));
        pm.setUrl(propertiesMap.get("url"));
        pm.setUsername(propertiesMap.get("username"));
        pm.setPassword(propertiesMap.get("password"));
        pm.setOtaWeb1Url(propertiesMap.get("otaWeb1Url"));
        pm.setOtaWeb3Url(propertiesMap.get("otaWeb3Url"));
        pm.setOtaWeb4Url(propertiesMap.get("otaWeb4Url"));
        pm.setOtaWeb5Url(propertiesMap.get("otaWeb5Url"));
        pm.setOtaWeb8Url(propertiesMap.get("otaWeb8Url"));
        pm.setOtaWeb8Url2(propertiesMap.get("otaWeb8Url2"));
        pm.setOtaWeb9Url(propertiesMap.get("otaWeb9Url"));
        pm.setOtaWeb11Url(propertiesMap.get("otaWeb11Url"));
        return pm;
    }


    /**
     * 读取properties的全部信息
     * Jun 26, 2010 9:21:01 PM
     *
     * @throws FileNotFoundException 配置文件没有找到
     * @throws IOException           关闭资源文件，或者加载配置文件错误
     * @author
     */
    public static Map<String, String> readAllProperties(String filename) throws Exception {
        props = new Properties();
        InputStream in = new BufferedInputStream(new FileInputStream(filename));
        props.load(in);
        //关闭资源
        in.close();
        //保存所有的键值
        Map<String, String> map = new HashMap<String, String>(16);
        Enumeration en = props.propertyNames();
        while (en.hasMoreElements()) {
            String key = (String) en.nextElement();
            String property = props.getProperty(key);
            map.put(key, property);
        }
        return map;
    }
}
