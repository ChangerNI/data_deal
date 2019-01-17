package com.zafu.nichang.datadeal.hotel.model;
/**
 * @author MountainTop
 * @version 1.0 2018-07-16
 */
public class Constant {
    /**
     * 携程相关正则表达式
     */
    public static final String OTA_WEB_1_REG_2="(id=\"lblAmount\">)([\\d\\D]{0,5})(<\\/b>)";
    public static final String OTA_WEB_1_REG_3="(<span class=\"hotel_num\">1<\\/span>)([\\d\\D]{0,30})(<\\/a><\\/h2>)";
    public static final String OTA_WEB_1_REG_4="(<p class=\"hotel_item_htladdress\">)([\\d\\D]{0,100})(<a href=\"javascript:;\")";
    public static final String OTA_WEB_1_REG_5="(很抱歉，暂时无法找到符合您要求的酒店。)";
    /**
     * 驴妈妈相关正则表达式
     */
    public static final String OTA_WEB_4_REG_1="(id=\"mainHotelLeft\")([\\d\\D]*)(class=\"mainRight\")";
    public static final String OTA_WEB_4_REG_2="(title=\")([\\d\\D]{0,50})(\">)";
    public static final String OTA_WEB_4_REG_3="(class=\"proInfo-address\">)([\\d\\D]{0,100})(<a href=\"javascript:\" class=\"pa-map\">)";
    public static final String OTA_WEB_4_REG_4="(非常抱歉，没有找到符合您条件的酒店)";
    /**
     * 其他常亮
     */
    public static final String CONSTANT_STRING_ONE="1";
    public static final Integer CONSTANT_INTEGER_TEN=12;
    /**
     * 美团相关正则表达式
     *
     */
    //名字
    public static final String OTA_WEB_9_REG_2="(data-query=\"utm_source=meituanweb\">)([\\d\\D]{0,50})(<\\/a><div class=\"item-eval-info)";
    //地址
    public static final String OTA_WEB_9_REG_3="(class=\"address ellipsis\">)([\\d\\D]{0,50})(<\\/span>)";
    //没有
    public static final String OTA_WEB_9_REG_4="(对不起，没有符合条件的商家)";
    //几家
    public static final String OTA_WEB_9_REG_5="(data-cateid=\")([\\d\\D]{0,5})(\">)";
    //崩溃
    public static final String OTA_WEB_9_REG_6="(抱歉，页面暂时无法访问... )";
    /**
     * 大众
     */
    public static final String OTA_WEB_8_REG_1= "(\"shopName\":\")([\\d\\D]{0,50})(\",\"shopUrl\")";
    public static final String OTA_WEB_8_REG_2="(\"regionName\":\")([\\d\\D]{0,100})(\",\"regionId\")";
    public static final String OTA_WEB_8_REG_3="(\"id\":\")([\\d]{0,20})(\",\"picArray\")";
    public static final String OTA_WEB_8_REG_4="(\"totalHits\":)([\\d]{0,10})(};)";
    public static final String OTA_WEB_8_REG_5="(<span class=\"hotel-address\">地址：)([\\d\\D]{0,100})(</span> <span class=\"hotel-metro\">)";

    /**
     * 飞猪
     */
    public static final String OTA_WEB_11_REG_1= "(抱歉,无法找到符合您要求的酒店.建议您更改条件重新搜索)";
    public static final String OTA_WEB_11_REG_2= "(data-name=\")([\\d\\D]{0,50})(\">)";
    public static final String OTA_WEB_11_REG_3="(<span class=\"address\" title=\")([\\d\\D]{0,100})(\">)";
    public static final String OTA_WEB_11_REG_4="(<span class=\"result-number\" id=\"J_ResultNumber\">1</span>)";
    /**
     * 同城
     */
    public static final String OTA_WEB_3_REG_1="(有<span>1</span>家酒店)";
    public static final String OTA_WEB_3_REG_2="(<i class=\"super-high\">)([\\d\\D]{0,50})(</i>)";
    public static final String OTA_WEB_3_REG_3="(<i class=\"add\">)([\\d\\D]{0,100})(</i>)([\\d\\D]{0,200})(<i class=\"map-btn\">)";
}
