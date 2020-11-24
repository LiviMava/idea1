package com.jd.userinfo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jd.userinfo.dao.IUserInfoDao;
import com.jd.vo.UserInfo;

@Service
public class UserInfoService implements IUserInfoService {
	
	@Autowired
	private IUserInfoDao userInfoDao;

	public List<UserInfo> page(int pageNo, String name, String mobile) {
		int pageSize=7;
		int index=(pageNo-1)*pageSize;
		if(name!=null&&!"".equals(name)) {
			name="%"+name+"%";
		}
		return userInfoDao.page(index, pageSize, name, mobile);
	}
	public int pageTotal(String name, String mobile) {
		int count = userInfoDao.getCount(name, mobile);
		int pageSize=7;
		if(name!=null&&!"".equals(name)) {
			name="%"+name+"%";
		}
		return count%pageSize==0?count/pageSize:count/pageSize+1;
	}
	public int delete(String id) {
		return userInfoDao.delete(id);
	}
	public int add(UserInfo userInfo) {
		userInfo.setId(UUID.randomUUID().toString());
		return userInfoDao.add(userInfo);
	}
	public UserInfo get(String id) {
		return userInfoDao.get(id);
	}
	public int update(UserInfo userInfo) {
		return userInfoDao.update(userInfo);
	}
}
