package com.shangguan.order.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shangguan.order.VO.ResultVO;
import com.shangguan.order.converter.OrderForm2OrderDTOConverter;
import com.shangguan.order.dto.OrderDTO;
import com.shangguan.order.enums.ResultEnum;
import com.shangguan.order.exception.OrderException;
import com.shangguan.order.form.OrderForm;
import com.shangguan.order.service.OrderService;
import com.shangguan.order.utils.ResultVOUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * 1. 参数校验
	 * 2. 查询商品信息（调用商品服务）
	 * 3. 计算总价
	 * 4. 扣库存（调用商品服务）
	 * 5. 订单入库
	 * @return 
	 */
	@RequestMapping("/create")
	public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
			BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			logger.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
		}
		//orderForm -> orderDTO
		OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
		if (CollectionUtils.isEmpty(orderDTO.getOrderDetails())) {
            logger.error("【创建订单】购物车信息为空");
            throw new OrderException(ResultEnum.CART_EMPTY);
        }

        OrderDTO result = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", result.getOrderId());
        return ResultVOUtil.success(map);
		
	}
	
}
