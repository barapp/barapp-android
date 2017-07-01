package com.develmagic.quellio.basket;

import android.widget.TextView;

import com.develmagic.quellio.MainActivity;
import com.develmagic.quellio.list.Product;
import com.develmagic.quellio.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mejmo on 25.01. 2017.
 */
public class Basket extends ArrayList<ProductQuantity> {

    private static Basket instance;
    private BasketAdapter adapter;

    public static Basket getInstance() {
        if (instance == null)
            instance = new Basket();
        return instance;
    }

    public float getSummary() {
        float f = 0f;
        for (ProductQuantity p : this) {
            f += p.getTotalPrice();
        }
        return Util.round(f, 2);
    }

    public ProductQuantity getProductQuantityById(int id) {
        for (ProductQuantity p : this) {
            if (p.getProductId() == id)
                return p;
        }
        return null;
    }

    public void removeProductId(int id) {
        this.remove(getProductQuantityById(id));
    }

//    public int getTypesCount() {
//        List<Integer> ids = new ArrayList<>();
//        for (ProductQuantity p : this) {
//            if (!ids.contains(p.getId()))
//                ids.add(p.getId());
//        }
//        return ids.size();
//    }
//
//    public int getIdCount(int id) {
//        int result = 0;
//        for (Product p : this) {
//            if (p.getId() == id)
//                result++;
//        }
//        return result;
//    }

    public BasketAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(BasketAdapter adapter) {
        this.adapter = adapter;
    }

    public void updateUI() {
        TextView summary = MainActivity.orderSummary;
        TextView count = MainActivity.orderCount;
        summary.setText(Basket.getInstance().getSummary()+" EUR ");
        count.setText("("+Basket.getInstance().size()+")");
        getAdapter().notifyDataSetChanged();
    }



}
