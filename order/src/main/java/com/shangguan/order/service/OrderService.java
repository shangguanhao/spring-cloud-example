package com.shangguan.order.service;

import org.springframework.stereotype.Service;

import com.shangguan.order.dto.OrderDTO;

@Service
public interface OrderService {
	
	/**
	 * 创建订单
	 * @param orderDTO
	 * @return
	 */
	OrderDTO create(OrderDTO orderDTO);
}
