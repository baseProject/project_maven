package com.zhangjiaolong.auth.dao.impl;

import org.springframework.stereotype.Repository;
import com.zhangjiaolong.auth.dao.IResourceDao;
import com.zhangjiaolong.auth.model.Resources;
import com.zhangjiaolong.frame.dao.BaseDAOImpl;

@Repository("IResourceDao")
public class ResourceDaoImpl extends BaseDAOImpl<Resources,Integer> implements IResourceDao{

	public ResourceDaoImpl() {
		super(Resources.class);
	}
}
