package com.zhangjiaolong.auth.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.zhangjiaolong.auth.dao.IUserDao;
import com.zhangjiaolong.auth.model.User;
import com.zhangjiaolong.auth.service.IUserService;
import com.zhangjiaolong.frame.dao.BaseDAO;
import com.zhangjiaolong.frame.service.Impl.BaseServiceImpl;

@SuppressWarnings("restriction")
@Service("iUserService")
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements IUserService{
	@Resource
	private IUserDao userDao;
	
	@Override
	public BaseDAO<User, Long> getDao() {
		return userDao;
	}

	@Override
	public User queryAndRoleByPrimaryKey(Map<String, Object> params) {
		return userDao.queryAndRoleByPrimaryKey(params);
	}
}
