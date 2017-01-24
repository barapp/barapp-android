package com.develmagic.quellio.basket;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.develmagic.quellio.R;
import com.develmagic.quellio.list.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mejmo on 24.01. 2017.
 */

public class BasketProductsFragment extends Fragment {

    public List<Product> getProductsList() {
        return productsList;
    }

    private List<Product> productsList = new ArrayList<>();
    private RecyclerView basketProducts;
    private static BasketProductsFragment instance;

    public BasketAdapter getBasketAdapter() {
        return basketAdapter;
    }

    private BasketAdapter basketAdapter;

    public void addProduct(Product product) {
        productsList.add(product);
        refreshLayout();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        basketProducts = (RecyclerView) inflater.inflate(R.layout.basket_product_list, container, false);
        this.basketAdapter = new BasketAdapter(getContext());
        basketProducts.setAdapter(basketAdapter);
//        refreshLayout();
        instance = this;
        return basketProducts;
    }

    public void refreshLayout() {
//        for (Product products_list_product : productsList) {
            LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.basket_product_mini, null);
            basketProducts.addView(linearLayout);
//        basketProducts.addView(linearLayout);

//        }
    }

    private LinearLayout getLayout() {
        return (LinearLayout)getView();
    }

    public static BasketProductsFragment getInstance() {
        return instance;
    }

}
