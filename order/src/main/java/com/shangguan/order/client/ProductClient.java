package com.shangguan.order.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shangguan.order.client.ProductClient.ProductClientFallback;
import com.shangguan.order.dto.CartDTO;
import com.shangguan.order.entity.ProductInfo;


@FeignClient(name = "product",fallback = ProductClientFallback.class)
public interface ProductClient {
	
	@RequestMapping("/msg")
	String productMsg();

	@RequestMapping("/product/listForOrder")
	public List<ProductInfo> listForOrder(@RequestBody List<String> productIdList);

	@RequestMapping("/product/decreaseStock")
	public void decreaseStock(@RequestBody List<CartDTO> cartDTOList);
	
	@Component
	class ProductClientFallback implements ProductClient{

		@Override
		public String productMsg() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<ProductInfo> listForOrder(List<String> productIdList) {
			return null;
		}

		@Override
		public void decreaseStock(List<CartDTO> cartDTOList) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
}
