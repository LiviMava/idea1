package com.jd.userinfo.service;

import java.util.List;

import com.jd.vo.UserInfo;

public interface IUserInfoService {

	List<UserInfo> page(int pageNo, String name, String mobile);

	int pageTotal(String name, String mobile);
	
	int delete(String id);
	
	int add(UserInfo userInfo);
	
	UserInfo get(String id);

	int update(UserInfo userInfo);
}
