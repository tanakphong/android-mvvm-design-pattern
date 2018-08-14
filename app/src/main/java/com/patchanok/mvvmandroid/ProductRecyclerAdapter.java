package com.patchanok.mvvmandroid;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.patchanok.mvvmandroid.databinding.ProductItemBinding;
import com.patchanok.mvvmandroid.model.ProductDetail;

import java.util.ArrayList;

/**
 * Created by patchanok on 9/17/2017 AD.
 */

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ViewHolder> {

    private ArrayList<ProductDetail> productDetails;

    @Override
    public ProductRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ProductItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.product_item,
                        parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.productItemBinding.setProductItem(productDetails.get(position));
        holder.bind();

    }

    @Override
    public int getItemCount() {
        return productDetails == null ? 0 : productDetails.size();
    }

    public void setItemview(ArrayList<ProductDetail> productDetails) {
        this.productDetails = productDetails;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ProductItemBinding productItemBinding;

        public ViewHolder(ProductItemBinding binding) {
            super(binding.getRoot());
            productItemBinding = DataBindingUtil.bind(itemView);
        }

        public void bind() {
            productItemBinding.executePendingBindings();
        }

    }
}
