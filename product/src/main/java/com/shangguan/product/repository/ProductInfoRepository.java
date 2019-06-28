package com.shangguan.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shangguan.product.entity.ProductInfo;


public interface ProductInfoRepository extends JpaRepository<ProductInfo, String>{

	List<ProductInfo> findByProductStatus(Integer productStatus);
	
	List<ProductInfo> findByProductIdIn(List<String> productIdList);
}
