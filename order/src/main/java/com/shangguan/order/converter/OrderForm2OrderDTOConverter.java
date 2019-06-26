package com.shangguan.order.converter;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shangguan.order.dto.OrderDTO;
import com.shangguan.order.entity.OrderDetail;
import com.shangguan.order.enums.ResultEnum;
import com.shangguan.order.exception.OrderException;
import com.shangguan.order.form.OrderForm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderForm2OrderDTOConverter {

	private static final Logger logger = LoggerFactory.getLogger(OrderForm2OrderDTOConverter.class);
	
    public static OrderDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        } catch (Exception e) {
            logger.error("【json转换】错误, string={}", orderForm.getItems());
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetails(orderDetailList);

        return orderDTO;
    }
}