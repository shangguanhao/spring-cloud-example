package com.shangguan.client;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.shangguan.order.OrderApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class ClientApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Before
    public void init() {
        System.out.println("开始测试-----------------");
    }
 
    @After
    public void after() {
        System.out.println("测试结束-----------------");
    }

}
