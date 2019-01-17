package com.zafu.nichang.datadeal.house.model;


/**
 * 配置
 *
 * @author MountainTop
 * @version 1.0 2018-07-16
 */
public class PropertiesModel {
    /**
     * 驱动
     */
    private String driver;
    /**
     * url
     */
    private String url;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 大众点评url
     */
    private String dazhongurl;

    /**
     * 大众点评详情页面
     */
    private String dazhongurldetal;

    /**
     * 图片url
     */
    private String dazhongimgurl;

    /**
     * 配置cookie
     */
    private String cookie;

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getDazhongimgurl() {
        return dazhongimgurl;
    }

    public void setDazhongimgurl(String dazhongimgurl) {
        this.dazhongimgurl = dazhongimgurl;
    }

    public String getDazhongurldetal() {
        return dazhongurldetal;
    }

    public void setDazhongurldetal(String dazhongurldetal) {
        this.dazhongurldetal = dazhongurldetal;
    }

    public String getDazhongurl() {
        return dazhongurl;
    }

    public void setDazhongurl(String dazhongurl) {
        this.dazhongurl = dazhongurl;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
