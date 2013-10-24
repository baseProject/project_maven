package com.zhangjiaolong.home.web.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zhangjiaolong.frame.common.PaginationParameter;

@Controller
@RequestMapping(value="/")
public class IndexController {
	
	@RequestMapping(value = "/index.html")
	public String listUser(Model model, String userName,Integer types, PaginationParameter pagination,HttpServletRequest request){
		HttpSession session=request.getSession();
		if(session!=null){
			
		}
		return "index";
	}
}
