package com.zafu.nichang.datadeal.webspider.util;

import org.junit.Test;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import static org.junit.Assert.*;

/**
 * @author 朱文赵
 * @date 2019/1/22 14:07
 */
public class RegUtilTest {

    @Test
    public void testLoadProperties() throws IOException {
        Properties prop = new Properties();
        prop.load(this.getClass().getResourceAsStream("/properties_web.properties"));
        Enumeration en = prop.propertyNames();
    }
}