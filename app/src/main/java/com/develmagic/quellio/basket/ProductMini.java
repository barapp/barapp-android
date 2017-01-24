package com.develmagic.quellio.basket;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.develmagic.quellio.R;
import com.develmagic.quellio.list.AddToBasketListener;

/**
 * Created by mejmo on 23.01. 2017.
 */

public class ProductMini extends LinearLayout {

    private int id;
    private String name;
    private float price;

    public ProductMini(Context context) {
        super(context);
    }

    public ProductMini(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProductMini(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ProductMini(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public static ProductMini newInstance(LayoutInflater inflater, int id, String title, float price) {
        ProductMini product = (ProductMini) inflater.inflate(R.layout.basket_product_mini, null);
        product.id = id;
        product.setOnClickListener(new RemoveFromBasketListener(product, null));

        return product;
    }

    private void click() {

    }

}
