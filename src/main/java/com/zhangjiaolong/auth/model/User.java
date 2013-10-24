package com.zhangjiaolong.auth.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class User implements Serializable{
	private static final long serialVersionUID = 8378060113583695632L;
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 登录名称
	 */
	private String loginName;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 真实名称
	 */
	private String name;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 用户性别
	 */
	private Integer gender; //0为男，1为女
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 所属角色
	 */
	private Set<Role> roles;
	/**
	 * 用户拥有权限
	 */
	private Set<Resources> resources;
	/**
	 * 是否锁定
	 */
	private boolean lock=false;
	/**
	 * 是否删除
	 */
	private boolean del=false;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isLock() {
		return lock;
	}
	public void setLock(Boolean lock) {
		this.lock = lock;
	}
	public boolean isDel() {
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
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public Set<Resources> getResources() {
		return resources;
	}
	public void setResources(Set<Resources> resources) {
		this.resources = resources;
	}
}
