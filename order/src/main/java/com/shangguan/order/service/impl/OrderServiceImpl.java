package com.shangguan.order.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangguan.order.client.ProductClient;
import com.shangguan.order.dto.CartDTO;
import com.shangguan.order.dto.OrderDTO;
import com.shangguan.order.entity.OrderDetail;
import com.shangguan.order.entity.OrderMaster;
import com.shangguan.order.entity.ProductInfo;
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
	
	@Autowired
	private ProductClient productClient;
	
	@Override
	public OrderDTO create(OrderDTO orderDTO) {
		
		String orderId = KeyUtil.genUniqueKey();
		
		//todo 2. 查询商品信息（调用商品服务）
		List<String> productIdList = orderDTO.getOrderDetails().stream()
				.map(OrderDetail::getProductId)
				.collect(Collectors.toList());
		List<ProductInfo> productInfoList = productClient.listForOrder(productIdList);
		
		//读redis
		//减库存并将新值重新设置进去redis，需要redis分布式锁
		
		//数据库订单入库异常，需要手动回滚redis
		
		//todo 3. 计算总价
		BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
		for(OrderDetail orderDetail : orderDTO.getOrderDetails()) {
			for(ProductInfo productInfo : productInfoList) {
				if (productInfo.getProductId().equals(orderDetail.getProductId())) {
					orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity()))
							.add(orderAmount);
					BeanUtils.copyProperties(productInfo, orderDetail);
					orderDetail.setOrderId(orderId);
					orderDetail.setDetailId(KeyUtil.genUniqueKey());
					//订单详情入库
					orderDetailRepository.save(orderDetail);
				}
			}
		}
		
		//todo 4. 扣库存（调用商品服务）
		List<CartDTO> cartDTOList = orderDTO.getOrderDetails().stream()
				.map(e -> new CartDTO(e.getProductId(),e.getProductQuantity()))
				.collect(Collectors.toList());;
		productClient.decreaseStock(cartDTOList);
		
		//5. 订单入库
		OrderMaster orderMaster = new OrderMaster();
		orderDTO.setOrderId(orderId);
		BeanUtils.copyProperties(orderDTO, orderMaster);
		orderMaster.setOrderAmount(orderAmount);
		orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
		orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
		orderMaster.setCreateTime(new Date());
		orderMaster.setUpdateTime(new Date());
		orderMasterRepository.save(orderMaster);
		return orderDTO;
	}

}
