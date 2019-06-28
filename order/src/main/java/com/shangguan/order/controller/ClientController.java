package com.shangguan.order.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.shangguan.order.client.ProductClient;
import com.shangguan.order.dto.CartDTO;
import com.shangguan.order.entity.ProductInfo;

@RestController
public class ClientController {

	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ProductClient productClient;
	
	@RequestMapping("/getProductMsg")
	public String getProductMsg() {
		//第一种方式（直接使用restTemplate，url写死）
//		RestTemplate restTemplate = new RestTemplate();
//		String response = restTemplate.getForObject("http://localhost:8080/msg", String.class);
//		System.out.println(response);
//		return response;
			
//		//第二种方式（使用loadBalancerClient通过应用名获取url，然后再使用restTemplate）
//		RestTemplate restTemplate = new RestTemplate();
//		ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
//		String host = serviceInstance.getHost();
//		int port = serviceInstance.getPort();
//		String url = String.format("http://%s:%s", host, port)+"/msg";
//		String response = restTemplate.getForObject(url, String.class);
//		System.out.println(response);
//		return response;
		
		//第三种方式（利用注解@LoadBalanced，可在restTemplate里面使用应用的名字）
//		String response = restTemplate.getForObject("http://PRODUCT/msg", String.class);
//		System.out.println(response);
//		return response;
		
		String response = productClient.productMsg();
		return response;	
		
	}
	
	@RequestMapping("/getProductList")
	public String getProductList() {
		List<ProductInfo> productInfoList = productClient.listForOrder(Arrays.asList("164103465734242707"));
		return "ok";
	}
	
	@RequestMapping("/productDecreaseStock")
	public String productDecreaseStock() {
		productClient.decreaseStock(Arrays.asList(new CartDTO("164103465734242707", 1)));
		return "ok";
	}
	
}
