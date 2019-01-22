package com.zafu.nichang.datadeal.webspider.util;

import com.zafu.nichang.datadeal.webspider.model.PropertiesModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 读取配置文件工具类
 *
 * @author 倪畅
 * @version 1.0 2019-01-16
 */
public class PropertiesUtil {

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
//        pm.setWebVegetableUrl(propertiesMap.get("webVegetableUrl"));
//        pm.setWebFruitUrl(propertiesMap.get("webFruitUrl"));
//        pm.setWebMeatUrl(propertiesMap.get("webMeatUrl"));
//        pm.setWebAquaticUrl(propertiesMap.get("webAquaticUrl"));
//        pm.setWebOilUrl(propertiesMap.get("webOilUrl"));

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
        try(final InputStream resourceAsStream = Class.forName(PropertiesUtil.class.getName()).getResourceAsStream(filename)) {
            Properties props = new Properties();
            props.load(resourceAsStream);
            //保存所有的键值
            Map<String, String> map = new HashMap<>(16);
            Enumeration en = props.propertyNames();
            while (en.hasMoreElements()) {
                String key = (String) en.nextElement();
                String property = props.getProperty(key);
                map.put(key, property);
            }
            return map;
        }
    }
}
