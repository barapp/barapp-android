package com.develmagic.quellio.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;

import com.develmagic.quellio.R;

public class ProductList extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    public static ProductList newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        ProductList fragment = new ProductList();
//        fragment.featuredProducts = featuredProducts;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // setting up fragment
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        GridLayout productsListView = (GridLayout) LayoutInflater.from(getContext()).inflate(R.layout.products_list, null);

        View product1 = (Button) LayoutInflater.from(getContext()).inflate(R.layout.product, null);
        View product2 = (Button) LayoutInflater.from(getContext()).inflate(R.layout.product, null);

        productsListView.addView(product1);
        productsListView.addView(product2);

        return productsListView;
    }
}