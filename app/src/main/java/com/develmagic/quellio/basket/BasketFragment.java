package com.develmagic.quellio.basket;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.develmagic.quellio.R;
import com.develmagic.quellio.list.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mejmo on 24.01. 2017.
 */

public class BasketFragment extends Fragment {

    private RecyclerView basketProducts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        Basket.getInstance().setAdapter(new BasketAdapter(getContext()));
        basketProducts = (RecyclerView) inflater.inflate(R.layout.basket_product_list, container, false);
        basketProducts.setAdapter(Basket.getInstance().getAdapter());
        basketProducts.setLayoutManager(layoutManager);

        return basketProducts;
    }

}
