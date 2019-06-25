package com.shangguan.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shangguan.product.entity.ProductCategory;


@Service
public interface CategoryService {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
