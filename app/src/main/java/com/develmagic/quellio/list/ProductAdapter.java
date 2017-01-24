package com.develmagic.quellio.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.develmagic.quellio.R;

/**
 * Created by mejmo on 23.01. 2017.
 */

public class ProductAdapter extends BaseAdapter {
    private Context mContext;

    public ProductAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }



    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        Product product = Product.newInstance(LayoutInflater.from(mContext), 10, "Test", 1.5f);
        return  product;

    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.ic_beer, R.drawable.ic_beer, R.drawable.ic_beer, R.drawable.ic_beer,
            R.drawable.ic_beer, R.drawable.ic_beer, R.drawable.ic_beer, R.drawable.ic_beer,
            R.drawable.ic_beer
    };
}
