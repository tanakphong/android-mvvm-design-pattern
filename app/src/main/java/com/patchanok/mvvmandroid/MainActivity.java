package com.patchanok.mvvmandroid;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.patchanok.mvvmandroid.model.Product;
import com.patchanok.mvvmandroid.viewmodel.ProductViewModel;

public class MainActivity extends AppCompatActivity {

    private ProductViewModel productViewModel;
    private SwipeRefreshLayout swipeRefresh;
    private ProgressBar progressRecyclerview;

    private ProductRecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        initialView();
        getProduct();
    }

    private void getProduct() {

        productViewModel.getProductList().observe(MainActivity.this, new Observer<Product>() {
            @Override
            public void onChanged(@Nullable Product product) {
                progressRecyclerview.setVisibility(View.GONE);
                assert product != null;
                recyclerAdapter.setItemview(product.getProductDetailList());
            }
        });
    }

    private void initialView() {
        int column = 2;
        progressRecyclerview =  findViewById(R.id.progressRecyclerview);
        progressRecyclerview.setVisibility(View.VISIBLE);

        swipeRefresh =  findViewById(R.id.swipeRefresh);
        swipeRefresh.setEnabled(true);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getProduct();
                swipeRefresh.setEnabled(false);
            }
        });

        RecyclerView recyclerView =  findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, column));
        recyclerAdapter = new ProductRecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);

    }
}
