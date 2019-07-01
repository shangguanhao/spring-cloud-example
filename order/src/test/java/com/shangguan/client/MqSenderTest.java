package com.shangguan.client;

import java.util.Date;

import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqSenderTest extends ClientApplicationTests {

	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Test
	public void send() {
		amqpTemplate.convertAndSend("myQueue","now" + new Date());
	}
}
