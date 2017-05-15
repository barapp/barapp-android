package com.develmagic.quellio.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.develmagic.quellio.Category;
import com.develmagic.quellio.service.BackendQuery;
import com.develmagic.quellio.service.dto.ProductDTO;

import java.util.List;

/**
 * Created by mejmo on 23.01. 2017.
 */

public class ProductAdapter extends BaseAdapter {
    private Context mContext;
    private Category category;
    private List<ProductDTO> items;


    public ProductAdapter(Context c, Category category) {
        mContext = c;
        this.category = category;
        this.items = BackendQuery.getByCategory(category);
    }

    public int getCount() {
        return this.items.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ProductDTO dto = items.get(position);
        Product product = Product.newInstance(LayoutInflater.from(mContext), dto.getId(), dto.getName(), dto.getPrice());
        return  product;
    }

}
