package com.shangguan.order.controller;

import java.util.Arrays;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {
	
	//超时设置
//	@HystrixCommand(commandProperties = {
//			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
//	})
	
	
	@HystrixCommand(commandProperties = {
	@HystrixProperty(name = "circuitBreaker.enabled", value = "true"),  				//设置熔断
	@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),	//请求数达到后才计算
	@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //休眠时间窗
	@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),	//错误率
	})
	@RequestMapping("getProductInfoList")
	public String getProductInfoList() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.postForObject("http://localhost:8080/product/listForOrder", 
				Arrays.asList("157875196366160022"), 
				String.class);
	}
	
	private String fallback() {
		return "太拥挤了，请稍后再试~~";
	}
	
	private String defaultFallback() {
		return "默认提示：太拥挤了，请稍后再试~~";
	}
	
}
