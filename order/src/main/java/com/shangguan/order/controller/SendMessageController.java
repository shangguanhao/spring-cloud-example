package com.shangguan.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shangguan.order.dto.OrderDTO;
import com.shangguan.order.message.StreamClient;

@RestController
public class SendMessageController {

	@Autowired
	private StreamClient StreamClient;
	
//	@GetMapping("/sendMessage")
//	public void process() {
//		String message = "now" + new Date();
//		StreamClient.output().send(MessageBuilder.withPayload(message).build());
//	}
	
	/**
	 * 发送OrderDTO对象
	 */
	@GetMapping("/sendMessage")
	public void process() {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setOrderId("123456");
		StreamClient.output().send(MessageBuilder.withPayload(orderDTO).build());
	}
	
	
}
