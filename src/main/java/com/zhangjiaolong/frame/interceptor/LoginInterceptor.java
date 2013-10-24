package com.zhangjiaolong.frame.interceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zhangjiaolong.frame.common.GlobalConfigure;
import com.zhangjiaolong.frame.common.ThreadLocalWrapper;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		Object appContext = ThreadLocalWrapper.get(GlobalConfigure.SPRING_APPLICATION_CONTEXT_KEY);
		if(appContext == null){
			ServletContext servletContext = request.getSession().getServletContext();
			ThreadLocalWrapper.put(GlobalConfigure.SPRING_APPLICATION_CONTEXT_KEY, 
						WebApplicationContextUtils.getWebApplicationContext(servletContext));
		}
		String contextPath = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+contextPath+"/";
		
		request.setAttribute("path", contextPath);
		request.setAttribute("basePath", basePath);
		return super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
}
