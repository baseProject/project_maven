package com.zhangjiaolong.frame.common;

import java.util.Properties;

import org.apache.commons.lang.StringUtils;

import com.zhangjiaolong.frame.utils.PropertiesUtils;

public class GlobalConfigure {

	public static String FILE_SERVER_LOCAL_PATH = null;
	
	public static String FILE_SERVER_NAME=null;
	
	public static String RESOURCES_URL=null;

	static {
		String os = System.getProperties().getProperty("os.name");
		Properties application = PropertiesUtils.getProperties("/META-INF/config/application.properties");
		if(StringUtils.startsWithIgnoreCase(os, "win")){
			FILE_SERVER_LOCAL_PATH = application.getProperty("windows.file.server.local.path");
		} else {
			FILE_SERVER_LOCAL_PATH = application.getProperty("linux.file.server.local.path");
		}
		FILE_SERVER_NAME=application.getProperty("fileServer");
		
		RESOURCES_URL=application.getProperty("resourcesURL");
	}
	
	public static final String SPRING_APPLICATION_CONTEXT_KEY = GlobalConfigure.class.getName() + "_SPRING_APPLICATION_CONTEXT_KEY";
	
	public static final String[] NO_INTERCEPTOR = {"/main/login.html","/main/dologin.html", "/main/dologout.html"};
	
	public static final String IMAGE_REGEX_TYPE = "image/[\\w|-]+";

	public static final String WEB_PAGING_VIEW_NAME = "pageView";
	
	public static final int DEFAULT_PAGE_SIZE = 20;
	
	public static final int MAX_PAGE_SIZE = 100;
	
	public static final String PAGINATION_SQL_SORT_FIELD = "sortField";
	
	public static final String PAGINATION_SQL_SORT_ORDER = "sortOrder";
	
	public static final String PAGINATION_SQL = "pagination";
	
	public static final String PAGINATION_SQL_START = "start";

	public static final String PAGINATION_SQL_END = "end";
	
	public static int orderIndex=0;
	
	public static final int SPECIALNEWSTYPE=2;
	
	public static final Integer MIDDLEWIDTH=640;
	public static final Integer MIDDLHEIGTH=160;
	public static final Integer SMALLWIDTH=170;
	public static final Integer SMALLHEIGTH=120;

	public static final int M_PAGE_SIZE=2;//客户端默认显示记录数
	
	public static int sendno=1;
	public static String app_key="6e07bcf5e265addcf047ad03";
	public static String platform="android,ios";
	public static int time_to_live=864000;
	public static String masterSecret="893b70addf05d372baf6e4d5";
	public static boolean isTuiSong=false;
	
	public static String INITPASSWORD="123456";
}
