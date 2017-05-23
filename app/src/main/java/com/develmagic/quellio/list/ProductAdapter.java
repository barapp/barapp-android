package com.develmagic.quellio.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.develmagic.quellio.Category;
import com.develmagic.quellio.MainActivity;
import com.develmagic.quellio.service.BackendService;
//import com.develmagic.quellio.service.ServiceGenerator;
import com.develmagic.quellio.service.ServiceGenerator;
import com.develmagic.quellio.service.dto.ProductDTO;
import com.develmagic.quellio.service.dto.ProductDTOList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
        this.items = new ArrayList<>();
        for (ProductDTO dto : MainActivity.getInstance().getItems().getProducts()) {
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
        ProductDTO dto = items.get(position);
        Product product = Product.newInstance(LayoutInflater.from(mContext), dto.getId(), dto.getName(), dto.getPrice(), dto.getImageUrl());
        return product;
    }

}
