package com.patchanok.mvvmandroid.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by patchanok on 9/16/2017 AD.
 */

public class Product {
    private int status = 0;
    private String message = "";
    @SerializedName("result")
    private ArrayList<ProductDetail> productDetailList = new ArrayList<>();

    public Product(int status, String message, ArrayList<ProductDetail> productDetails) {
        this.status = status;
        this.message = message;
        this.productDetailList = productDetails;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<ProductDetail> getProductDetailList() {
        return productDetailList;
    }
}
