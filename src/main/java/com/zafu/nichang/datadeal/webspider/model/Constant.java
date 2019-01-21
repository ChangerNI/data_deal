package com.zafu.nichang.datadeal.webspider.model;

import java.util.regex.Pattern;

/**
 * @author 倪畅
 * @version 1.0 2019-01-16
 */
public class Constant {
    /**
     * 相关正则表达式
     */
    //产品名称
    public static final String OTA_WEB_PRODUCT_REG = "(padding-left:5px;\">)([\\d\\D]{0,12})(<\\/td>)";
    //产品属性
    public static final String OTA_WEB_DETAIL_REG = "(<td>)([\\d\\D]{1,7})(<\\/td>)";
    //产品日期
    public static final String OTA_WEB_DATE_REG = "(<td>)([\\d\\D]{10})(<\\/td><td><\\/td><\\/tr>)";
    //产品块
    public static final String OTA_WEB_HTML_BLOCK_REG = "(padding-left:5px;\">)([\\d\\D]{0,170})(<td><\\/td><\\/tr>)";
    //最大页数
    public static final String OTA_WEB_HTML_LAST_PAGE_REG = "(list\\/)([\\d\\D]{0,10})(.shtml\" title=\"尾页\">)";
    /**
     * 为了提高效率，进行重复调用的compile方法常量化
     */
    public static final Pattern OTA_WEB_PRODUCT_REG_PATTERN = Pattern.compile(OTA_WEB_PRODUCT_REG);
    public static final Pattern OTA_WEB_DETAIL_REG_PATTERN = Pattern.compile(OTA_WEB_DETAIL_REG);
    public static final Pattern OTA_WEB_DATE_REG_PATTERN = Pattern.compile(OTA_WEB_DATE_REG);
    public static final Pattern OTA_WEB_HTML_BLOCK_REG_PATTERN = Pattern.compile(OTA_WEB_HTML_BLOCK_REG);
    public static final Pattern OTA_WEB_HTML_LAST_PAGE_REG_PATTERN = Pattern.compile(OTA_WEB_HTML_LAST_PAGE_REG);

}
