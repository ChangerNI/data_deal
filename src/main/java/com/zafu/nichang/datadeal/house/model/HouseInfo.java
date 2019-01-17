package com.zafu.nichang.datadeal.house.model;
import java.util.List;

/**
 * 农家乐  基本信息
 */
public class HouseInfo {
    /**
     * id
     */
    private String id;

    /**
     * 名称
     */
    private String houseName;
    /**
     * 区域
     */
    private String houseArea;
    /**
     * 地址
     */
    private String houseAddress;
    /**
     * 电话
     */
    private String telephone;
    /**
     * 营业时间
     */
    private String businessTime;

    /**
     * 图片集合
     */
    List<HousePic> housePicList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getHouseArea() {
        return houseArea;
    }

    public void setHouseArea(String houseArea) {
        this.houseArea = houseArea;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(String businessTime) {
        this.businessTime = businessTime;
    }

    public List<HousePic> getHousePicList() {
        return housePicList;
    }

    public void setHousePicList(List<HousePic> housePicList) {
        this.housePicList = housePicList;
    }

    @Override
    public java.lang.String toString() {
        return "HouseInfo{" +
                "id=" + id +
                ", houseName=" + houseName +
                ", houseArea=" + houseArea +
                ", houseAddress=" + houseAddress +
                ", telephone=" + telephone +
                ", businessTime=" + businessTime +
                ", housePicList=" + housePicList +
                '}';
    }
}
