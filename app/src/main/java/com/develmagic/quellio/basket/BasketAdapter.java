package com.develmagic.quellio.basket;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.develmagic.quellio.MainActivity;
import com.develmagic.quellio.R;
import com.develmagic.quellio.list.Product;
import com.develmagic.quellio.service.dto.ProductDTO;

/**
 * Created by mejmo on 23.01. 2017.
 */

public class BasketAdapter extends BaseAdapter {

    private Context mContext;

    public BasketAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return Basket.getInstance().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ProductQuantity productQuantity = Basket.getInstance().get(position);
        Product p = productQuantity.getProduct();
        ProductMini pMini = ProductMini.newInstance(LayoutInflater.from(mContext), position, p.getImage());

        if (productQuantity.getQuantity() > 1) {
            TextView textView = (TextView) pMini.findViewById(R.id.count);
            textView.setText(String.valueOf(productQuantity.getQuantity()));
            textView.setVisibility(View.VISIBLE);
        }

        return pMini;
    }


}
