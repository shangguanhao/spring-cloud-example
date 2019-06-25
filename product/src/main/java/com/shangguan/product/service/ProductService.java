package com.shangguan.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shangguan.product.entity.ProductInfo;

@Service
public interface ProductService {

	/**
	 * 查询所有在家商品列表
	 */
	List<ProductInfo> findUpAll();
}
