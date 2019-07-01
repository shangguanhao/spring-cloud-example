package com.shangguan.order.message;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class MqReceiver {

	//1. @RabbitListener(queues = "myQueue")
	//2. 自动创建队列 @RabbitListener(queuesToDeclare = @Queue("myQueue"))
	//3. 自动创建队列，Exchange和Queue绑定
	@RabbitListener(bindings = @QueueBinding(
				value = @Queue("myQueue"),
				exchange = @Exchange("myExchange")
			))
    public void process(String message) {
        System.out.println("MqReceiver:" + message);
    }
}
