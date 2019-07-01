package com.shangguan.order.message;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.shangguan.order.entity.ProductInfo;
import com.shangguan.order.utils.JsonUtil;

@Component
public class ProductInfoReceiver {

	private static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@RabbitListener(queuesToDeclare = @Queue("productInfo"))
	public void process(String message) {
		//message -> ProductInfo
		List<ProductInfo> productInfoList= (List<ProductInfo>) JsonUtil.fromJson(message,new TypeReference<List<ProductInfo>>() {});
		System.out.println("从队列中接收到消息" + message);
		
		//存储到redis中
		for (ProductInfo productInfo : productInfoList ) {
			stringRedisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE, productInfo.getProductId()), 
					String.valueOf(productInfo.getProductStock()));
		}
	}
}
