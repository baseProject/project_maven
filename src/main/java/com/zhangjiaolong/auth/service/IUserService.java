package com.zhangjiaolong.auth.service;

import java.util.Map;

import com.zhangjiaolong.auth.model.User;
import com.zhangjiaolong.frame.service.BaseService;

public interface IUserService extends BaseService<User, Long>{
	User queryAndRoleByPrimaryKey(Map<String,Object> params);
}
