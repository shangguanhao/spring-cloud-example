package com.shangguan.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangguan.product.entity.ProductInfo;
import com.shangguan.product.enums.ProductStatusEnum;
import com.shangguan.product.repository.ProductInfoRepository;
import com.shangguan.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductInfoRepository productInfoRepository;
	
	@Override
	public List<ProductInfo> findUpAll() {
		return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
	}

}
