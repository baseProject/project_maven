package com.zhangjiaolong.frame.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.zhangjiaolong.frame.common.GlobalConfigure;

public class NewsBrokeUtils {

	public static String downloadFilePath(String[] dirs){
		StringBuilder dirBuilder = new StringBuilder();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date theDate = new Date();
		dirBuilder.append(GlobalConfigure.FILE_SERVER_LOCAL_PATH).append(File.separator);
		for(String dir : dirs){
			dirBuilder.append(dir).append(File.separator);
		}
		dirBuilder.append(df.format(theDate));
		return dirBuilder.toString();
	}
	
}
