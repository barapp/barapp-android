package com.develmagic.quellio.basket;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.develmagic.quellio.list.Product;

/**
 * Created by mejmo on 23.01. 2017.
 */

public class BasketAdapter extends RecyclerView.Adapter<ProductMiniHolder> {
    private Context mContext;

    public BasketAdapter(Context c) {
        mContext = c;
    }

    @Override
    public ProductMiniHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProductMiniHolder(ProductMini.newInstance(LayoutInflater.from(mContext), 10, "Test", 1.5f));
    }

    @Override
    public void onBindViewHolder(ProductMiniHolder holder, int position) {
//        Product product = Basket.getInstance().get(position);
        holder.setId(position);
        //TODO icon
    }

    @Override
    public int getItemCount() {
        return Basket.getInstance().size();
    }

}
