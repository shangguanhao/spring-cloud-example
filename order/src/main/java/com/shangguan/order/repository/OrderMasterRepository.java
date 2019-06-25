package com.shangguan.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.shangguan.order.entity.OrderMaster;

@Service
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

}