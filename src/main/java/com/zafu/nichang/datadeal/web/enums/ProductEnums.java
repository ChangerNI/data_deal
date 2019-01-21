package com.zafu.nichang.datadeal.web.enums;

/**
 * @author 朱文赵
 * @date 2019/1/21 16:49
 */
public enum  ProductEnums {

    /** 蔬菜 */
    VEGETABLE("http://www.xinfadi.com.cn/marketanalysis/1/list/???.shtml"),
    FRUIT("http://www.xinfadi.com.cn/marketanalysis/2/list/???.shtml"),
    MEAT("http://www.xinfadi.com.cn/marketanalysis/3/list/???.shtml"),
    AQUATIC("http://www.xinfadi.com.cn/marketanalysis/4/list/???.shtml"),
    OIL("http://www.xinfadi.com.cn/marketanalysis/5/list/???.shtml"),
    ;

    private String url;

    ProductEnums(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }}
