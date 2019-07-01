package com.shangguan.order.message;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.shangguan.order.dto.OrderDTO;

@Component
@EnableBinding(StreamClient.class)
public class StreamReceiver {
	
//	@StreamListener(StreamClient.INPUT)
//	@SendTo(StreamClient.OUTPUT)
//	public Object processInput(Object message) {
//		System.out.println(message.toString());
//		return message;
//	}
//	
//	@StreamListener(StreamClient.OUTPUT)
//	public void processOutput(Object message) {
//		System.out.println(message);
//	}
	
	/*
	 * 接收OrderDTO对象
	 */
	@StreamListener(StreamClient.INPUT)
	@SendTo(StreamClient.OUTPUT)
	public Object process(OrderDTO message) {
		System.out.println(message.toString());
		return message;
	}
	
	
	
}
