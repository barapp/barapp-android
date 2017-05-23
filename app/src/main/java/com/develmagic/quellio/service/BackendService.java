package com.develmagic.quellio.service;

import com.develmagic.quellio.service.dto.ProductDTO;
import com.develmagic.quellio.service.dto.ProductDTOList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by mejmo on 23.5.2017.
 */


public interface BackendService {

    @GET("items/")
    Call<ProductDTOList> listItems();

}
