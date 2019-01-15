package com.zafu.nichang.datadeal.house.model;

public class Constant {


    /**
     * 相关正则表达式
     */
    /**
     * 列表
     */
    public static final String DAZHONG_REG_1="(id=\"shop-all-list\")([\\d\\D]*)(class=\"page\")";
    /**
     * 分页
     */
    public static final String DAZHONG_REG_2="(class=\"page\")([\\d\\D]*)(class=\"sear-result no-result\")";
    /**
     * 分页数
     */
    public static final String DAZHONG_REG_3="(data-ga-page=\")([\\d]{0,5})(\")";


    /**
     * 名称
     */
    public static final String DAZHONG_REG_4="(title=\")([\\d\\D]{0,50})(\" target=\"_blank\")";

    /**
     * 星级  2
     */
    public static final String DAZHONG_REG_5="(title=\")([\\d\\D]{0,50})(\"></span>)";
    /**
     * 区域  4
     */
    public static final String  DAZHONG_REG_6="(data-click-name=\"shop_tag_region_click\")([\\d\\D]{0,200})(span class=\"tag\">)([\\d\\D]{0,50})(</span>)";

    /**
     * 地址  2
     */
    public static final String  DAZHONG_REG_7="(<span class=\"addr\">)([\\d\\D]{0,50})(</span>)";


    /**
     * ID
     */
    public static final String  DAZHONG_REG_8="(href=\"http://www.dianping.com/shop/)([\\d]{0,10})(\" data-click-name)";


    /**
     * 详情页 有用的html
     */
    public static final String  DAZHONG_REG_9= "(id=\"basic-info\")([\\d\\D]*)(id=\"myreview-wrapper\")";

    /**
     * 电话
     */
    public static final String  DAZHONG_REG_10="(<p class=\"expand-info tel\"> <span class=\"info-name\">电话：</span>)([\\d\\D]{0,20})(</p>)";

    /**
     * 营业时间
     */
    public static final String  DAZHONG_REG_11= "(<span class=\"info-name\">营业时间：</span> <span class=\"item\">)([\\d\\D]{0,200})(</span>)";
}
