package com.shangguan.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer>{
	
	List<ProductCategory> findByCategoryTypeIn(List<Integer> CategoryTypeList);
}
