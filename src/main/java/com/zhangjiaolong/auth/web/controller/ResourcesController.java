package com.zhangjiaolong.auth.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhangjiaolong.auth.model.Resources;
import com.zhangjiaolong.auth.service.IResourceService;
import com.zhangjiaolong.frame.common.GlobalConfigure;
import com.zhangjiaolong.frame.common.PageView;
import com.zhangjiaolong.frame.common.PaginationParameter;

@SuppressWarnings("restriction")
@Controller
@RequestMapping(value="/resources")
public class ResourcesController {
	
	@Resource
	private IResourceService resourceService;
	
	@RequestMapping(value = "/list.html")
	public String listUser(Model model, String userName,Integer types, PaginationParameter pagination){
		Map<String,Object> params=new HashMap<String,Object>();
		PageView<Resources> pageView = this.resourceService.queryBypage(params, pagination);
		model.addAttribute(GlobalConfigure.WEB_PAGING_VIEW_NAME, pageView);
		return "user/listResources";
	}
}
