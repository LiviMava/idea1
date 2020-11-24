package com.jd.userinfo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jd.vo.UserInfo;

public interface IUserInfoDao {
	List<UserInfo> page(@Param("index")int index, @Param("pageSize")int pageSize, @Param("name")String name, @Param("mobile")String mobile);
	
	int getCount(@Param("name")String name, @Param("mobile")String mobile);
	
	int delete(@Param("id")String id);
	
	int add(UserInfo userInfo);
	
	UserInfo get(@Param("id")String id);

	int update(UserInfo userInfo);
}
