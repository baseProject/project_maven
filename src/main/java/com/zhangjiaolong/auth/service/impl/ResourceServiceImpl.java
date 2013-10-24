package com.zhangjiaolong.auth.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhangjiaolong.auth.dao.IResourceDao;
import com.zhangjiaolong.auth.model.Resources;
import com.zhangjiaolong.auth.service.IResourceService;
import com.zhangjiaolong.frame.dao.BaseDAO;
import com.zhangjiaolong.frame.service.Impl.BaseServiceImpl;

@SuppressWarnings("restriction")
@Service("iResourceService")
public class ResourceServiceImpl extends BaseServiceImpl<Resources, Integer> implements IResourceService{
	
	@Resource
	private IResourceDao resourceDao;
	
	@Override
	public BaseDAO<Resources, Integer> getDao() {
		return resourceDao;
	}
}
