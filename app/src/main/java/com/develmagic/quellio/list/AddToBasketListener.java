package com.develmagic.quellio.list;

import android.view.View;

import com.develmagic.quellio.basket.BasketProductsFragment;

/**
 * Created by mejmo on 24.01. 2017.
 */

public class AddToBasketListener implements View.OnClickListener {

    private Product product;

    public AddToBasketListener(Product product) {
        this.product = product;
    }

    @Override
    public void onClick(View v) {
        BasketProductsFragment.getInstance().getProductsList().add(this.product);
        BasketProductsFragment.getInstance().getBasketAdapter().notifyDataSetChanged();
//        BasketProductsFragment.getInstance().addProduct();
    }

}
