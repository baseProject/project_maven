package com.zhangjiaolong.auth.dao;
import com.zhangjiaolong.auth.model.Role;
import com.zhangjiaolong.frame.dao.BaseDAO;

public interface IRoleDao extends BaseDAO<Role,Integer>{
	void saveResources(Role role);
}
