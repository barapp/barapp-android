package com.develmagic.quellio.service;

import com.develmagic.quellio.Category;
import com.develmagic.quellio.list.Product;
import com.develmagic.quellio.service.dto.MemberDTO;
import com.develmagic.quellio.service.dto.OrderResult;
import com.develmagic.quellio.service.dto.ProductDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by mejmo on 12.5.2017.
 */

public class BackendQuery {

    public static List<ProductDTO> getByCategory(Category category) {
        List<ProductDTO> res = new ArrayList<>();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(10);
        productDTO.setCategory(Category.COFFEE);
        productDTO.setPrice(1.5f);
        productDTO.setName(category.name()+" Nejaka kava");
        res.add(productDTO);
        res.add(productDTO);
        res.add(productDTO);
        res.add(productDTO);
        res.add(productDTO);
        res.add(productDTO);
        res.add(productDTO);
        res.add(productDTO);
        res.add(productDTO);
        res.add(productDTO);
        res.add(productDTO);
        res.add(productDTO);
        return res;
    }

    public static ProductDTO getById() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setCategory(Category.COFFEE);
        productDTO.setPrice(1.5f);
        productDTO.setId(10);
        productDTO.setName("Nejaka kava");
        return productDTO;
    }

    public static OrderResult order(long userId, List<Product> productsList) {
        OrderResult result = new OrderResult();
//        result.setResultCode(OrderResult.ERROR);
//        result.setError("User does not have something assigned");
        result.setResultCode(OrderResult.OK);
        return result;
    }

    public static List<MemberDTO> getMembers() {
        List<MemberDTO> list = new ArrayList<>();
        String s = "MNBVCXZLKJHGFDSAQWERTYUIOP";
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            MemberDTO dto = new MemberDTO();
            dto.setName(s.substring(r.nextInt(s.length())));
            dto.setId(i);
            dto.setTeam("Taxify");
            list.add(dto);
        }
        return list;
    }

}
