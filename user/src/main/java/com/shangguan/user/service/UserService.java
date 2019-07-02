package com.shangguan.user.service;

import org.springframework.stereotype.Service;

import com.shangguan.user.entity.UserInfo;

@Service
public interface UserService {
	
	UserInfo findByOpenid(String openid);
}
