package com.zafu.nichang.datadeal.dao;

/**
 * @author ：倪畅
 * @date ：Created in 2019/1/21 22:11
 * @description：获取产品信息实体类
 * @modified By：
 * @version: 1.0$
 */
public class ProductDao {
    public String productName;
    public Double minPrice;
    public Double avgPrice;
    public Double maxPrice;
    public String sizeType;
    public String dateTime;
    public String productType;


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getSizeType() {
        return sizeType;
    }

    public void setSizeType(String sizeType) {
        this.sizeType = sizeType;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProductDao{");
        sb.append("productName='").append(productName).append('\'');
        sb.append(", minPrice=").append(minPrice);
        sb.append(", avgPrice=").append(avgPrice);
        sb.append(", maxPrice=").append(maxPrice);
        sb.append(", sizeType='").append(sizeType).append('\'');
        sb.append(", dateTime='").append(dateTime).append('\'');
        sb.append(", productType='").append(productType).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
