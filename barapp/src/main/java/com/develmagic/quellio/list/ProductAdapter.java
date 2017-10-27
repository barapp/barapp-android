package com.develmagic.quellio.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.develmagic.quellio.MainActivity;

import java.util.ArrayList;
import java.util.List;

import rii.solutions.campus.data.Category;
import rii.solutions.campus.data.MenuItem;

//import com.develmagic.quellio.service.ServiceGenerator;

/**
 * Created by mejmo on 23.01. 2017.
 */

class ProductAdapter extends BaseAdapter {
    private Context mContext;
    private Category category;
    private List<MenuItem> items;


    ProductAdapter(Context c, Category category) {
        mContext = c;
        this.category = category;
        this.items = new ArrayList<>();
        for (MenuItem dto : MainActivity.getInstance().getItems()) {
            if (dto.getCategory().equals(category)) {
                this.items.add(dto);
            }
        }
//        this.items = BackendQuery.getByCategory(category);
//        JsonObjectRequest jsObjRequest = new JsonObjectRequest
//                (Request.Method.GET, "http://localhost:8000/items/", null, new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        new Gson().fromJson(response.toString(), ProductDTOList.class);
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // TODO Auto-generated method stub
//                        System.err.println("KOKOT");
//                    }
//                });
//        MainActivity.getInstance().getmRequestQueue().add(jsObjRequest);


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
        MenuItem dto = items.get(position);
        return Product.newInstance(
                LayoutInflater.from(mContext),
                dto.getId(),
                dto.getName(),
                dto.getPrice(),
                dto.getImageUrl());
    }

}