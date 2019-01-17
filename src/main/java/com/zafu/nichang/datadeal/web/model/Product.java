package com.zafu.nichang.datadeal.web.model;

import java.util.LinkedList;

public class Product {

    private String productName;
    private Double minPrice;
    private Double avgPrice;
    private Double maxPrice;
    private String sizeType;
    private String unit;
    private String dateTime;

    public Product() {
    }

    public Product(LinkedList<String> productNameLists,
                   LinkedList<String> productDetailsLists,
                   LinkedList<String> productDateLists) {
        this.productName = productNameLists.removeFirst();
        this.minPrice = Double.valueOf(productDetailsLists.removeFirst());
        this.avgPrice = Double.valueOf(productDetailsLists.removeFirst());
        this.maxPrice = Double.valueOf(productDetailsLists.removeFirst());
        this.sizeType = productDetailsLists.removeFirst();
        this.unit = productDetailsLists.removeFirst();
        this.dateTime = productDateLists.removeFirst();
    }

    public Product(String productName) {
        this.productName = productName;
    }

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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("productName='").append(productName).append('\'');
        sb.append(", minPrice=").append(minPrice);
        sb.append(", avgPrice=").append(avgPrice);
        sb.append(", maxPrice=").append(maxPrice);
        sb.append(", sizeType='").append(sizeType).append('\'');
        sb.append(", unit='").append(unit).append('\'');
        sb.append(", dateTime='").append(dateTime).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
