package com.develmagic.quellio.basket;

import android.view.View;

import com.develmagic.quellio.list.Product;

/**
 * Created by mejmo on 24.01. 2017.
 */

public class RemoveFromBasketListener implements View.OnClickListener {

    private ProductMini product;
    private Product productParent;

    public RemoveFromBasketListener(ProductMini product, Product productParent) {
        this.product = product;
    }

    @Override
    public void onClick(View v) {
        Basket.getInstance().remove(this.product);
        Basket.getInstance().getAdapter().notifyDataSetChanged();
    }

}
