package com.zhangjiaolong.auth.dao.impl;

import org.springframework.stereotype.Repository;

import com.zhangjiaolong.auth.dao.IRoleDao;
import com.zhangjiaolong.auth.model.Role;
import com.zhangjiaolong.frame.dao.BaseDAOImpl;

@Repository("IRoleDao")
public class RoleDaoImpl extends BaseDAOImpl<Role,Integer> implements IRoleDao{
	public RoleDaoImpl() {
		super(Role.class);
	}

	@Override
	public void saveResources(Role role) {
		this.sqlSession.delete(entityMapper+"deleteResources", role.getId());
		this.sqlSession.insert(entityMapper+"saveResources", role);
	}

}
