package com.zhangjiaolong.auth.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zhangjiaolong.auth.dao.IUserDao;
import com.zhangjiaolong.auth.model.User;
import com.zhangjiaolong.frame.dao.BaseDAOImpl;

@Repository("IUserDao")
public class UserDaoImpl extends BaseDAOImpl<User,Long> implements IUserDao{

	public UserDaoImpl() {
		super(User.class);
	}

	@Override
	public void saveRoles(User user) {
		this.sqlSession.delete(entityMapper+"deleteRoles", user.getId());
		this.sqlSession.insert(entityMapper+"saveRoles", user);
	}

	@Override
	public User queryAndRoleByPrimaryKey(Map<String, Object> params) {
		return this.sqlSession.selectOne(entityMapper+"queryAndRoleByPrimaryKey", params);
	}
}
