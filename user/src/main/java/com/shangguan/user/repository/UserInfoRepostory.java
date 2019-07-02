package com.shangguan.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shangguan.user.entity.UserInfo;

public interface UserInfoRepostory extends JpaRepository<UserInfo, String> {

	UserInfo findByOpenid(String openid);
}
