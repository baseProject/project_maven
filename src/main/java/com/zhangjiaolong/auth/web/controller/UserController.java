package com.zhangjiaolong.auth.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhangjiaolong.auth.model.User;
import com.zhangjiaolong.auth.model.UserInfo;
import com.zhangjiaolong.auth.service.IUserService;
import com.zhangjiaolong.auth.web.pojo.UserForm;
import com.zhangjiaolong.frame.common.GlobalConfigure;
import com.zhangjiaolong.frame.common.JSONResponse;
import com.zhangjiaolong.frame.common.PageView;
import com.zhangjiaolong.frame.common.PaginationParameter;

@SuppressWarnings("restriction")
@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Resource
	private IUserService userService;
	
	@RequestMapping(value = "/list.html")
	public String listUser(Model model, String name,boolean del, PaginationParameter pagination){
		UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("name", name);
		params.put("del", del);
		params.put("currentUser", userInfo.getUsername());
		PageView<User> pageView = this.userService.queryBypage(params, pagination);
		model.addAttribute(GlobalConfigure.WEB_PAGING_VIEW_NAME, pageView);
		return "user/listuser";
	}
	/**
	 * 进入添加用户窗口
	 * @return
	 */
	@RequestMapping(value="/add.html")
	public String addUser(){
		return "user/add";
	}
	/**
	 * 添加用户信息
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/ajaxAddUser.html")
	public @ResponseBody JSONResponse ajaxAddUser(UserForm userForm){
		JSONResponse json = new JSONResponse();
		try {
			if(userForm.getId()!=null){
				if(userForm.getEmail()==null||userForm.getEmail().trim()==""){
					json.setMsg("邮箱不能为空!");
					json.setSuccess(false);
					return json;
				}
				Pattern pattern = Pattern.compile("[//w//.//-]+@([//w//-]+//.)+[//w//-]+",Pattern.CASE_INSENSITIVE);
				Matcher matcher = pattern.matcher(userForm.getEmail());
				if(matcher.matches()){
					json.setMsg("邮箱格式不正确!");
					json.setSuccess(false);
					return json;
				}
				User user=userService.queryByPrimaryKey(userForm.getId());
				user.setEmail(userForm.getEmail());
				user.setName(userForm.getName());
				user.setGender(userForm.getGender());
				this.userService.updateByEntity(user);
				json.setMsg("用户信息修改成功!");
				json.setSuccess(true);
			}else{
				if(userForm==null){
					json.setMsg("用户信息不能为空!");
					json.setSuccess(false);
					return json;
				}
				if(userForm.getEmail()==null||userForm.getEmail().trim()==""){
					json.setMsg("邮箱不能为空!");
					json.setSuccess(false);
					return json;
				}
				Pattern pattern = Pattern.compile("[//w//.//-]+@([//w//-]+//.)+[//w//-]+",Pattern.CASE_INSENSITIVE);
				Matcher matcher = pattern.matcher(userForm.getEmail());
				if(matcher.matches()){
					json.setMsg("邮箱格式不正确!");
					json.setSuccess(false);
					return json;
				}
				if(userForm.getEmail()==null||userForm.getEmail().trim()==""){
					json.setMsg("邮箱不能为空!");
					json.setSuccess(false);
					return json;
				}
				if(userForm.getLoginName()==null||userForm.getLoginName().trim()==""){
					json.setMsg("登录名不能为空!");
					json.setSuccess(false);
					return json;
				}
				if(userForm.getPassword()==null||userForm.getPassword().trim()==""){
					json.setMsg("密码不能为空!");
					json.setSuccess(false);
					return json;
				}
				if(userForm.getQueren_password()==null||userForm.getQueren_password().trim()==""){
					json.setMsg("确认密码不能为空!");
					json.setSuccess(false);
					return json;
				}
				if(!userForm.getPassword().equals(userForm.getQueren_password())){
					json.setMsg("密码和确认密码不一致!");
					json.setSuccess(false);
					return json;
				}
				Map<String, Object> params = new HashMap<String,Object>();
				params.put("loginName", userForm.getLoginName());
				User u=this.userService.queryByParameterSingle(params);
				if(u!=null&&u.getId()!=0){
					json.setMsg("登录名已存在!");
					json.setSuccess(false);
					return json;
				}
				Md5PasswordEncoder md5=new Md5PasswordEncoder();
				userForm.setPassword(md5.encodePassword(userForm.getPassword(), userForm.getLoginName()));
				userForm.setCreateTime(new Date());
				this.userService.insertByEntity(userForm);
				json.setMsg("添加用户信息成功!");
				json.setSuccess(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg(e.getMessage());
			json.setSuccess(false);
		}
		return json;
	}
	
	/**
	 * 进入修改用户窗口
	 * @return
	 */
	@RequestMapping(value="/edit.html")
	public String editUser(Model model,Long id){
		User user=this.userService.queryByPrimaryKey(id);
		if(user!=null) model.addAttribute("user", user);
		return "user/edit";
	}
	
	/**
	 * 删除用户信息
	 * @param idUser
	 * @return
	 */
	@RequestMapping(value="/ajaxDeleteUser.html")
	public @ResponseBody JSONResponse ajaxdeleteUser(Long idUser){
		JSONResponse json = new JSONResponse();
		if(idUser==null){
			json.setMsg("用户ID不能为空!");
			json.setSuccess(false);
			return json;
		}
		try {
			this.userService.deleteByPrimaryKey(idUser);
			json.setMsg("删除用户成功!");
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg(e.getMessage());
			json.setSuccess(false);
		}
		return json;
	}
	
	
	/**
	 * 进入添加用户窗口
	 * @return
	 */
	@RequestMapping(value="/toUpdatePass.html")
	public String toUpdatePass(Model model,Long id){
		model.addAttribute("id", id);
		return "user/updatePW";
	}
	
	/**
	 * 修改密码
	 * @return
	 */
	@RequestMapping(value="/updatePass.html")
	public @ResponseBody JSONResponse updatePass(Model model,Long id,String oldPassword,String password,String queren_password){
		JSONResponse json = new JSONResponse();
		try{
			if(oldPassword==null||"".equals(oldPassword.trim())){
				json.setSuccess(false);
				json.setMsg("旧密码不能为空！");
				return json;
			}
			if(password==null||"".equals(password.trim())){
				json.setSuccess(false);
				json.setMsg("新密码不能为空！");
				return json;
			}
			if(queren_password==null||"".equals(queren_password.trim())){
				json.setSuccess(false);
				json.setMsg("确认密码不能为空！");
				return json;
			}
			if(!password.equals(queren_password)){
				json.setSuccess(false);
				json.setMsg("密码与确认密码不一致！");
				return json;
			}
			Md5PasswordEncoder md5=new Md5PasswordEncoder();
			User user=userService.queryByPrimaryKey(id);
			if(!user.getPassword().equals(md5.encodePassword(oldPassword,user.getLoginName()))){
				json.setSuccess(false);
				json.setMsg("原密码错误！");
				return json;
			}
			user.setPassword(md5.encodePassword(password, user.getLoginName()));
			userService.updateByEntity(user);
			json.setMsg("修改成功");
			json.setSuccess(true);
		}catch(Exception e){
			e.printStackTrace();
			json.setMsg("修改失败,请稍后重试！");
			json.setSuccess(false);
		}
		return json;
	}
	
	
	/**
	 * 重置密码
	 * @return
	 */
	@RequestMapping(value="/updatePassword.html")
	public @ResponseBody JSONResponse updatePassword(Model model,Long id){
		JSONResponse json = new JSONResponse();
		try{
			User user=userService.queryByPrimaryKey(id);
			Md5PasswordEncoder md5=new Md5PasswordEncoder();
			user.setPassword(md5.encodePassword(GlobalConfigure.INITPASSWORD, user.getLoginName()));
			userService.updateByEntity(user);
			json.setMsg("重置成功");
			json.setSuccess(true);
		}catch(Exception e){
			e.printStackTrace();
			json.setMsg("重置失败,请稍后重试！");
			json.setSuccess(false);
		}
		return json;
	}
	
	/**
	 * 更改用户锁定转台
	 * @return
	 */
	@RequestMapping(value="/updateLock.html")
	public @ResponseBody JSONResponse updateLock(Model model,Long id,boolean lock){
		JSONResponse json = new JSONResponse();
		try{
			User user=new User();
			user.setId(id);
			user.setLock(lock);
			userService.updateByEntity(user);
			if(lock){
				json.setMsg("锁定成功！");
			}else{
				json.setMsg("解锁成功！");
			}
			json.setSuccess(true);
		}catch(Exception e){
			e.printStackTrace();
			if(lock){
				json.setMsg("锁定失败,请稍后重试！");
			}else{
				json.setMsg("解锁失败,请稍后重试！");
			}
			
			json.setSuccess(false);
		}
		return json;
	}
	
	/**
	 * 进入修改用户密码窗口
	 * @return
	 */
	@RequestMapping(value="/updateRole.html")
	public String updateRole(Model model,Integer id){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("id", id);
		userService.queryAndRoleByPrimaryKey(params);
		return "user/list.html";
	}
}
