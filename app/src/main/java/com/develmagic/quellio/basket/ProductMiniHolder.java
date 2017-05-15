package com.develmagic.quellio.basket;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.develmagic.quellio.R;

/**
 * Created by mejmo on 23.01. 2017.
 */

public class ProductMiniHolder extends RecyclerView.ViewHolder {

    private int id;
    private Drawable icon;

    public ProductMiniHolder(View itemView) {
        super(itemView);
    }

//    public static ProductMiniHolder newInstance(LayoutInflater inflater, int id, String title, float price) {
//        ProductMiniHolder product = P(roductMiniHolder) inflater.inflate(R.layout.basket_product_mini, null);
//        product.id = id;
//        product.setOnClickListener(new RemoveFromBasketListener(product, null));
//
//        return product;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
