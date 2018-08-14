package com.patchanok.mvvmandroid.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.patchanok.mvvmandroid.model.Product;
import com.patchanok.mvvmandroid.service.ApiService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by patchanok on 9/16/2017 AD.
 */

public class ProductViewModel extends ViewModel {

    private ApiService service;
    private MutableLiveData<Product> productMutableLiveData = new MutableLiveData<>();

    public ProductViewModel() {
        fetchDataProduct();
    }

    private void fetchDataProduct() {

        service = new ApiService();
        service.createService().getAllProduct(1).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                Observable.just(response.body())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subscribeData());
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });
    }

    private Observer<Product> subscribeData() {
        return new Observer<Product>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Product product) {
                productMutableLiveData.setValue(product);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    public MutableLiveData<Product> getProductList() {
        return productMutableLiveData;
    }
}
