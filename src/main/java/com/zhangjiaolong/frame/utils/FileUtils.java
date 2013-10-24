package com.zhangjiaolong.frame.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.zhangjiaolong.frame.common.GlobalConfigure;

public class FileUtils {
	
	/**
	 * 获取文件的后缀名
	 * @param fileName 文件名
	 * @return 文件后缀名,例如: .txt
	 */
	public static String getFileExtension(String fileName){
		if(StringUtils.isEmpty(fileName)){
			throw new RuntimeException("文件名称不能为空!");
		}
		int index = fileName.lastIndexOf(".");
		if(index == -1){
			throw new RuntimeException("文件名没有后缀名!");
		}
		return fileName.substring(index);
	}
	
	/**
	 * 上传文件到指定的目录中
	 * @param dirPath			上级目录
	 * @param originalFileName	原文件名
	 * @param targetFileName	目标文件名
	 * @param data				文件字节数据
	 * @return 图片可访问URL: /XXXXXX/XXXXXX.jpg
	 * @throws IOException
	 */
	public static String uploadFile(String dirPath, String originalFileName, String targetFileName, byte[] data) throws IOException {
		File localFile = new File(dirPath);
		if(!localFile.exists()){
			localFile.mkdirs();
		}
		StringBuilder builder = new StringBuilder();
		builder.append(dirPath);
		if(targetFileName == null || targetFileName.length() == 0){
			builder.append(System.currentTimeMillis()).append(getFileExtension(originalFileName));
		} else {
			builder.append(targetFileName);
		}
		targetFileName = builder.toString();
		org.apache.commons.io.FileUtils.writeByteArrayToFile(new File(targetFileName), data);
		
		String picUrl = targetFileName.replace(GlobalConfigure.FILE_SERVER_LOCAL_PATH, "");
		picUrl = picUrl.replaceAll("\\\\", "/");
		return picUrl;
	}
	
	public static String downloadNetFile(String urlPath, String fileName, String filePath) throws IOException {
		
		File sf=new File(filePath);  
		if(!sf.exists()){  
			sf.mkdirs();  
		}
		
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			URL url = new URL(urlPath);
			URLConnection conn = url.openConnection();
			conn.setConnectTimeout(5000);
			
			inputStream = conn.getInputStream();
			
			String fileFullPath = filePath  + File.separator + fileName;
			outputStream = new FileOutputStream(fileFullPath);
			byte[] buffer = new byte[1024*1024];
			while(true){
				int len = inputStream.read(buffer);
				if(len == -1){
					inputStream.close();
					break;
				}
				outputStream.write(buffer, 0 , len);
			}
			outputStream.flush();
			
			String downloadFile = fileFullPath.replace(GlobalConfigure.FILE_SERVER_LOCAL_PATH, GlobalConfigure.FILE_SERVER_NAME);
			downloadFile =downloadFile.replaceAll("\\\\", "/");
			return downloadFile;
		} finally {
			if(inputStream != null){
				inputStream.close();
				inputStream = null;
			}
			if(outputStream != null){
				outputStream.close();
				outputStream = null;
			}
		}
	}
	
	public static String doUploadFile(MultipartFile uploadFile) throws IOException{
		StringBuilder dirBuilder = new StringBuilder();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		dirBuilder.append(GlobalConfigure.FILE_SERVER_LOCAL_PATH).append(File.separatorChar).append("news").append(File.separatorChar)
				.append(dateFormat.format(new Date())).append(File.separatorChar);
		File localFile = new File(dirBuilder.toString());
		if(!localFile.exists()){
			localFile.mkdirs();
		}
		
		String fileName = uploadFile.getOriginalFilename();
		String attType = "";
		int index = fileName.lastIndexOf(".");
		if(index > 0) {
			attType = fileName.substring(index);
		}
		String localPath = dirBuilder.append(System.currentTimeMillis()).append(attType).toString();
		localFile = new File(localPath);
		OutputStream os;
		os = new FileOutputStream(localFile);
		os.write(uploadFile.getBytes());
		os.flush();
		os.close();
		
		String dbLink =localPath.replace(GlobalConfigure.FILE_SERVER_LOCAL_PATH, GlobalConfigure.FILE_SERVER_NAME);
		dbLink = dbLink.replaceAll("\\\\", "/");
		
		return dbLink;
	}
	
}
