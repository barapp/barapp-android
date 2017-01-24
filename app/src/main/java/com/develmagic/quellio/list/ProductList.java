package com.develmagic.quellio.list;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;


public class ProductList extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // setting up fragment
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ConstraintLayout viewPagerContainer = new ConstraintLayout(getContext());

        GridView productsListView = new GridView(getContext());
        productsListView.setNumColumns(3);
        productsListView.setVerticalSpacing(10);
        productsListView.setHorizontalSpacing(10);
        productsListView.setHorizontalScrollBarEnabled(false);
        productsListView.setVerticalScrollBarEnabled(false);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(20, 0, 20, 0);
        productsListView.setLayoutParams(lp);

        productsListView.setAdapter(new ProductAdapter(this.getContext()));
        return productsListView;

    }
}