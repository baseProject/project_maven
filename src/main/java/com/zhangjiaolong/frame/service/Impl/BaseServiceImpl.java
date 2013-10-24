package com.zhangjiaolong.frame.service.Impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import com.zhangjiaolong.frame.common.PageView;
import com.zhangjiaolong.frame.common.PaginationParameter;
import com.zhangjiaolong.frame.dao.BaseDAO;
import com.zhangjiaolong.frame.service.BaseService;

public class BaseServiceImpl<T, PK extends Serializable> implements BaseService<T, PK>{	
	
	@Override
	public long countByParameter(Map<String, Object> parameter) {
		return this.getDao().countByParameter(parameter);
	}

	@Override
	public int deleteBatchByPrimaryKey(Map<String, Object> parameter) {
		return this.getDao().deleteBatchByPrimaryKey(parameter);
	}

	@Override
	public int deleteByPrimaryKey(PK idPrimaryKey) {
		return this.getDao().deleteByPrimaryKey(idPrimaryKey);
	}

	@Override
	public int insertByEntity(T entity) {
		return this.getDao().insertByEntity(entity);
	}

	@Override
	public List<T> queryByParameter(Map<String, Object> parameter) {
		return this.getDao().queryByParameter(parameter);
	}

	@Override
	public T queryByPrimaryKey(PK idPrimaryKey) {
		return this.getDao().queryByPrimaryKey(idPrimaryKey);
	}

	@Override
	public int updateByEntity(T entity) {
		return this.getDao().updateByEntity(entity);
	}

	@Override
	public BaseDAO<T, PK> getDao() {
		return null;
	}

	@Override
	public PageView<T> queryBypage(Map<String, Object> params,
			PaginationParameter pagination) {
		return this.getDao().queryBypage(params, pagination);
	}

	@Override
	public T queryByParameterSingle(Map<String, Object> parameter) {
		return this.getDao().queryByParameterSingle(parameter);
	}
}
