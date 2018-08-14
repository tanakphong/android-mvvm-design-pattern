package com.patchanok.mvvmandroid.service;

import com.patchanok.mvvmandroid.model.Product;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by patchanok on 9/16/2017 AD.
 */

public interface IGetService {
    @GET("/products")
    Call<Product> getAllProduct(@Query("page") int per_page);
}
