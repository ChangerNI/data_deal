package com.zafu.nichang.datadeal.web.model;

import com.zafu.nichang.datadeal.web.Start;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class MyRunnable implements Runnable {
    public int pageCount;
    public Function<PropertiesModel, String> getUrl;
    public PropertiesModel propertiesModel;
    public List<Product> productList;
    public MyRunnable(){
    }
    public MyRunnable(int pageCount, Function<PropertiesModel, String> getUrl, PropertiesModel propertiesModel){
        this.pageCount = pageCount;
        this.getUrl = getUrl;
        this.propertiesModel = propertiesModel;
    }
    public List<Product> getproductLists(){
        run();
        return productList;
    }
    @Override
    public void run() {
        try {
           productList = Start.getProduct(pageCount, getUrl, propertiesModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
