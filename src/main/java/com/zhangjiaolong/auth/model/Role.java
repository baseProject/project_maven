package com.zhangjiaolong.auth.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class Role implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2332808284267885202L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 角色名称
	 */
	private String name;
	/**
	 * 拥有资源
	 */
	private Set<Resources> resources;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 是否删除
	 */
	private Boolean del=false;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Resources> getResources() {
		return resources;
	}
	public void setResources(Set<Resources> resources) {
		this.resources = resources;
	}
	public Boolean isDel() {
		return del;
	}
	public void setDel(Boolean del) {
		this.del = del;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
