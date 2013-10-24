package com.zhangjiaolong.auth.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.zhangjiaolong.auth.dao.IRoleDao;
import com.zhangjiaolong.auth.model.Role;
import com.zhangjiaolong.auth.service.IRoleService;
import com.zhangjiaolong.frame.dao.BaseDAO;
import com.zhangjiaolong.frame.service.Impl.BaseServiceImpl;

@SuppressWarnings("restriction")
@Service("iRoleService")
public class RoleServiceImpl extends BaseServiceImpl<Role, Integer> implements IRoleService{
	@Resource
	private IRoleDao roleDao;
	
	@Override
	public BaseDAO<Role, Integer> getDao() {
		return roleDao;
	}
}
