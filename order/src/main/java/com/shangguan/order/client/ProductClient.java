package com.shangguan.order.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shangguan.order.dto.CartDTO;
import com.shangguan.order.entity.ProductInfo;


@FeignClient(name = "product")
public interface ProductClient {
	
	@RequestMapping("/msg")
	String productMsg();

	@RequestMapping("/product/listForOrder")
	public List<ProductInfo> listForOrder(@RequestParam(value = "productIdList") List<String> productIdList);

	@RequestMapping("/product/decreaseStock")
	public void decreaseStock(@RequestBody List<CartDTO> cartDTOList);
	
}
