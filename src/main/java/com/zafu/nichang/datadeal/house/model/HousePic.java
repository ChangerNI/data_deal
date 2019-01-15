package com.zafu.nichang.datadeal.house.model;

/**
 * 农家乐   图片
 */
public class HousePic {

    /**
     * 外键
     */
    private String houseId;
    /**
     * 小图片
     */
    private String smallPicUrl;
    /**
     * 大图片
     */
    private String bigPicUrl;

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getSmallPicUrl() {
        return smallPicUrl;
    }

    public void setSmallPicUrl(String smallPicUrl) {
        this.smallPicUrl = smallPicUrl;
    }
    public String getBigPicUrl() {
        return bigPicUrl;
    }
    public void setBigPicUrl(String bigPicUrl) {
        this.bigPicUrl = bigPicUrl;
    }
}
