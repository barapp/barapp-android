package com.develmagic.quellio.basket;

import android.widget.TextView;

import com.develmagic.quellio.MainActivity;
import com.develmagic.quellio.list.Product;

import java.util.ArrayList;

/**
 * Created by mejmo on 25.01. 2017.
 */
public class Basket extends ArrayList<Product> {

    private static Basket instance;
    private BasketAdapter adapter;

    public static Basket getInstance() {
        if (instance == null)
            instance = new Basket();
        return instance;
    }

    public float getSummary() {
        float f = 0f;
        for (Product p : this) {
            f += p.getPrice();
        }
        return f;
    }

    public BasketAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(BasketAdapter adapter) {
        this.adapter = adapter;
    }

    public void updateUI() {
        TextView summary = MainActivity.orderSummary;
        TextView count = MainActivity.orderCount;
        summary.setText(Basket.getInstance().getSummary()+" EUR ");
        count.setText("("+Basket.getInstance().size()+")");
        getAdapter().notifyDataSetChanged();
    }



}
