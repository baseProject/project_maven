package com.zhangjiaolong.auth.web.pojo;

import com.zhangjiaolong.auth.model.User;

public class UserForm extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8444619001265722904L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private String queren_password;

	public String getQueren_password() {
		return queren_password;
	}
	public void setQueren_password(String querenPassword) {
		queren_password = querenPassword;
	}
}
