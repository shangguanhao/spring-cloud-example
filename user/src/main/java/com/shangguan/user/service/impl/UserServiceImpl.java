package com.shangguan.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangguan.user.entity.UserInfo;
import com.shangguan.user.repository.UserInfoRepostory;
import com.shangguan.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserInfoRepostory repostory;

	@Override
	public UserInfo findByOpenid(String openid) {
		return repostory.findByOpenid(openid);
	}
}
