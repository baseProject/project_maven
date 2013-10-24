package com.zhangjiaolong.frame.utils;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

  
  
/** 
 *  
 * 上传图片　工具类 大图片路径,生成小图片路径, 大图片文件名,生成小图片文名, 生成小图片宽度,生成小图片高度, 是否等比缩放(默认为true)) 
 *  
 * @author Administrator 
 *  
 */  
public class UploadUtil  
{  
    public static boolean zoomImage(String imagePath, String newPath, Integer width, Integer height) {
		boolean flag = false;
		FileInputStream srcFile=null;
		try {
			srcFile = new FileInputStream(imagePath);
			BufferedImage srcBufferImage = ImageIO.read(srcFile);
			BufferedImage scaledImage;  
			ScaleImage scaleImage= ScaleImage.getInstance();  
			scaledImage = scaleImage.imageZoomOut(srcBufferImage, width, height);  
	        FileOutputStream out = new FileOutputStream(newPath);  
	        ImageIO.write(scaledImage,newPath.substring(newPath.indexOf(".")+1,newPath.length()), out);
	        flag=true;
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(srcFile!=null){
				try {
					srcFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}
}
  