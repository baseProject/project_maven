package com.zhangjiaolong.auth.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhangjiaolong.auth.model.Role;
import com.zhangjiaolong.auth.model.User;
import com.zhangjiaolong.auth.service.IRoleService;
import com.zhangjiaolong.frame.common.GlobalConfigure;
import com.zhangjiaolong.frame.common.PageView;
import com.zhangjiaolong.frame.common.PaginationParameter;

@SuppressWarnings("restriction")
@Controller
@RequestMapping(value="/role")
public class RoleController {
	
	@Resource
	private IRoleService roleService;
	
	@RequestMapping(value = "/list.html")
	public String listUser(Model model, String userName,Integer types, PaginationParameter pagination){
		Map<String,Object> params=new HashMap<String,Object>();
		PageView<Role> pageView = this.roleService.queryBypage(params, pagination);
		model.addAttribute(GlobalConfigure.WEB_PAGING_VIEW_NAME, pageView);
		return "user/listRole";
	}
	/**
	 * 进入添加用户窗口
	 * @return
	 */
	@RequestMapping(value="/toAdd.html")
	public String addUser(){
		return "user/add";
	}
	
	/**
	 * 添加用户信息
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/addRole.html")
	public String ajaxAddUser(User user){
		return "success";
	}
	
	/**
	 * 进入修改用户窗口
	 * @return
	 */
	@RequestMapping(value="/toEdit.html")
	public String editUser(Model model,Integer id){
		Role role=this.roleService.queryByPrimaryKey(id);
		if(role!=null) model.addAttribute("role", role);
		return "user/edit";
	}
	
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/editRole.html")
	public String edit(Role role){
		return "success";
	}
	/**
	 * 删除用户信息
	 * @param idUser
	 * @return
	 */
	@RequestMapping(value="/delete.html")
	public String ajaxdeleteUser(Integer id){
		return "success";
	}
}
