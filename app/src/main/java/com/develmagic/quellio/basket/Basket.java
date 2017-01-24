package com.develmagic.quellio.basket;

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

    public BasketAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(BasketAdapter adapter) {
        this.adapter = adapter;
    }
}
