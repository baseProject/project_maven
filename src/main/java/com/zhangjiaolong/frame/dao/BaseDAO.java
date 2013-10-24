package com.zhangjiaolong.frame.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zhangjiaolong.frame.common.PageView;
import com.zhangjiaolong.frame.common.PaginationParameter;

public interface BaseDAO<T, PK extends Serializable> {
	/**
	 * 对象插入
	 * @param entity
	 * @return
	 */
	int insertByEntity(T entity);
	/**
	 * 对象更新
	 * @param entity
	 * @return
	 */
	int updateByEntity(T entity);
	/**
	 * 按primaryKey删除
	 * @param idPrimaryKey
	 * @return
	 */
	int deleteByPrimaryKey(PK idPrimaryKey);
	/**
	 * 批量删除
	 * @param parameter
	 * @return
	 */
	int deleteBatchByPrimaryKey(Map<String, Object> parameter);
	/**
	 * 查询总数
	 * @param parameter
	 * @return
	 */
	long countByParameter(Map<String, Object> parameter);

	List<T> queryByParameter(Map<String, Object> parameter);
	
	PageView<T> queryBypage(Map<String, Object> params,PaginationParameter pagination);
	
	T queryByParameterSingle(Map<String, Object> parameter);
	
	T queryByPrimaryKey(PK idPrimaryKey);
}
