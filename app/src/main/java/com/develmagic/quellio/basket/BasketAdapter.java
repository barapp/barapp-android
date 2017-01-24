package com.develmagic.quellio.basket;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.develmagic.quellio.list.Product;

/**
 * Created by mejmo on 23.01. 2017.
 */

public class BasketAdapter extends RecyclerView.Adapter<ProductMini> {
    private Context mContext;

    public BasketAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {



    }

    public Object getItem(int position) {
        ProductMini product = ProductMini.newInstance(LayoutInflater.from(mContext), 10, "Test", 1.5f);
        return product;
    }

    @Override
    public ProductMini onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ProductMini holder, int position) {

    }

    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        if (BasketProductsFragment.getInstance() == null)
            return 0;
        return BasketProductsFragment.getInstance().getProductsList().size();
    }

//    ProductMini product = ProductMini.newInstance(LayoutInflater.from(mContext), 10, "Test", 1.5f);
//    return product;

}
