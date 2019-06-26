package com.shangguan.order.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangguan.order.dto.OrderDTO;
import com.shangguan.order.entity.OrderMaster;
import com.shangguan.order.enums.OrderStatusEnum;
import com.shangguan.order.enums.PayStatusEnum;
import com.shangguan.order.repository.OrderDetailRepository;
import com.shangguan.order.repository.OrderMasterRepository;
import com.shangguan.order.service.OrderService;
import com.shangguan.order.utils.KeyUtil;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Autowired
	private OrderMasterRepository orderMasterRepository;
	
	@Override
	public OrderDTO create(OrderDTO orderDTO) {
		//todo 2. 查询商品信息（调用商品服务）
		//todo 3. 计算总价
		//todo 4. 扣库存（调用商品服务）
		
		//5. 订单入库
		OrderMaster orderMaster = new OrderMaster();
		orderDTO.setOrderId(KeyUtil.genUniqueKey());
		BeanUtils.copyProperties(orderDTO, orderMaster);
		orderMaster.setOrderAmount(new BigDecimal(5));
		orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
		orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
		return orderDTO;
	}

}
