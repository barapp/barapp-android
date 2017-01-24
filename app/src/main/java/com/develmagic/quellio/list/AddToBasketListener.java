package com.develmagic.quellio.list;

import android.view.View;

import com.develmagic.quellio.basket.Basket;
import com.develmagic.quellio.basket.BasketFragment;

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
        Basket.getInstance().add(this.product);
        Basket.getInstance().getAdapter().notifyDataSetChanged();
//        BasketFragment.getInstance().addProduct();
    }

}
