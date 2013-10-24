package com.zhangjiaolong.auth.dao;
import java.util.Map;

import com.zhangjiaolong.auth.model.User;
import com.zhangjiaolong.frame.dao.BaseDAO;

public interface IUserDao extends BaseDAO<User,Long>{
	void saveRoles(User user);

	User queryAndRoleByPrimaryKey(Map<String,Object> id);
}
