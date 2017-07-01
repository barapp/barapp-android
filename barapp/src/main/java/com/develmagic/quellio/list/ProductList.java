package com.develmagic.quellio.list;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;

import rii.solutions.campus.data.Category;


public class ProductList extends Fragment {

    private Category category;

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // setting up fragment
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        GridView productsListView = new GridView(getContext());
        productsListView.setNumColumns(3);
        productsListView.setVerticalSpacing(10);
        productsListView.setHorizontalSpacing(10);
        productsListView.setHorizontalScrollBarEnabled(false);
        productsListView.setVerticalScrollBarEnabled(true);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(20, 0, 20, 0);
        productsListView.setLayoutParams(lp);
        productsListView.setAdapter(new ProductAdapter(this.getContext(), this.category));
        return productsListView;

    }

}