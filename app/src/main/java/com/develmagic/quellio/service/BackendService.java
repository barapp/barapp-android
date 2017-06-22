package com.develmagic.quellio.service;

import com.develmagic.quellio.list.Product;
import com.develmagic.quellio.service.dto.MemberDTO;
import com.develmagic.quellio.service.dto.MemberDTOList;
import com.develmagic.quellio.service.dto.OrderResultDTO;
import com.develmagic.quellio.service.dto.ProductDTO;
import com.develmagic.quellio.service.dto.ProductDTOList;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by mejmo on 23.5.2017.
 */


public interface BackendService {

    @GET("/items/")
    Call<ProductDTOList> listItems();

    @GET("/members/")
    Call<MemberDTOList> listMembers();

    @POST("/make_transaction/")
    Call<OrderResultDTO> order(@Body JsonObject transactionData);

}
