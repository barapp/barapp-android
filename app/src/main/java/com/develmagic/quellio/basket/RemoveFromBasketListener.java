package com.develmagic.quellio.basket;

import android.view.View;

import com.develmagic.quellio.list.Product;

/**
 * Created by mejmo on 24.01. 2017.
 */

public class RemoveFromBasketListener implements View.OnClickListener {

    private ProductMiniHolder product;
    private Product productParent;

    public RemoveFromBasketListener(ProductMiniHolder product, Product productParent) {
        this.product = product;
    }

    @Override
    public void onClick(View v) {
//        BasketFragment.getInstance().getProductsList().add(this.product);
//        BasketFragment.getInstance().getBasketAdapter().notifyDataSetChanged();
//        BasketFragment.getInstance().addProduct();
    }

}
