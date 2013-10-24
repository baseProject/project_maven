package com.zhangjiaolong.auth.model;

import java.util.Date;
import java.util.Set;

public class Resources{
	private Integer id;
	private String name;
	private String url;
	private Boolean menu=false;
	private Date createTime;
	private Integer pId;
	private Set<Role> roles;
	
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public Integer getpId() {
		return pId;
	}
	public void setpId(Integer pId) {
		this.pId = pId;
	}
	public Boolean getMenu() {
		return menu;
	}
	public void setMenu(Boolean menu) {
		this.menu = menu;
	}
}
