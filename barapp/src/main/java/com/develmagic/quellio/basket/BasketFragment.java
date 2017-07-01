package com.develmagic.quellio.basket;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.develmagic.quellio.MainActivity;
import com.develmagic.quellio.R;
import com.develmagic.quellio.list.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mejmo on 24.01. 2017.
 */

public class BasketFragment extends Fragment {

    private GridView basketProducts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return basketProducts;
    }

}
