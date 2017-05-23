package com.develmagic.quellio.service;

import com.develmagic.quellio.list.Product;
import com.develmagic.quellio.service.dto.MemberDTO;
import com.develmagic.quellio.service.dto.MemberDTOList;
import com.develmagic.quellio.service.dto.OrderResultDTO;
import com.develmagic.quellio.service.dto.ProductDTO;
import com.develmagic.quellio.service.dto.ProductDTOList;

import java.util.List;

import retrofit2.Call;
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

    @POST("/make_order")
    Call<OrderResultDTO> order(long userId, List<Product> productDTOs);

}
