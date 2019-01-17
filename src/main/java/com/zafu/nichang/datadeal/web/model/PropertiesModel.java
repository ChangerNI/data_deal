package com.zafu.nichang.datadeal.web.model;


/**
 * 配置
 *
 * @author nichang
 * @version 1.0 2019-01-16
 */
public class PropertiesModel {
    /**
     * 驱动
     */
    private String driver;
    /**
     * 数据库url
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
     * 网站urls
     */
    private String webVegetableUrl;
    private String webFruitUrl;
    private String webMeatUrl;
    private String webAquaticUrl;
    private String webOilUrl;

    public String getWebVegetableUrl() {
        return webVegetableUrl;
    }

    public void setWebVegetableUrl(String webVegetableUrl) {
        this.webVegetableUrl = webVegetableUrl;
    }

    public String getWebFruitUrl() {
        return webFruitUrl;
    }

    public void setWebFruitUrl(String webFruitUrl) {
        this.webFruitUrl = webFruitUrl;
    }

    public String getWebMeatUrl() {
        return webMeatUrl;
    }

    public void setWebMeatUrl(String webMeatUrl) {
        this.webMeatUrl = webMeatUrl;
    }

    public String getWebAquaticUrl() {
        return webAquaticUrl;
    }

    public void setWebAquaticUrl(String webAquaticUrl) {
        this.webAquaticUrl = webAquaticUrl;
    }

    public String getWebOilUrl() {
        return webOilUrl;
    }

    public void setWebOilUrl(String webOilUrl) {
        this.webOilUrl = webOilUrl;
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
