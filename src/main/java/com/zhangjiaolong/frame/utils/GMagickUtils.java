package com.zhangjiaolong.frame.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.core.IdentifyCmd;
import org.im4java.process.ArrayListOutputConsumer;

public class GMagickUtils {
	
	private static String imageMagickPath = null;

	static {
		Properties application = PropertiesUtils.getProperties("/META-INF/config/application.properties");
		imageMagickPath = application.getProperty("image.magick.path");
	}
	
	public int getSize(String imagePath) {
		int size = 0;
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(imagePath);
			size = inputStream.available();
			inputStream.close();
			inputStream = null;
		} catch (FileNotFoundException e) {
			size = 0;
			System.out.println("文件未找到!");
		} catch (IOException e) {
			size = 0;
			System.out.println("读取文件大小错误!");
		} finally {
			// 可能异常为关闭输入流,所以需要关闭输入流
			if (null != inputStream) {
				try {
					inputStream.close();
				} catch (IOException e) {
					System.out.println("关闭文件读入流异常");
				}
				inputStream = null;

			}
		}
		return size;
	}

	/**
	 * 获得图片的宽度
	 * @param filePath 文件路径
	 * @return 图片宽度
	 */
	public int getWidth(String imagePath) {
		int line = 0;
		try {
			IMOperation op = new IMOperation();
			op.format("%w"); // 设置获取宽度参数
			op.addImage(1);
			IdentifyCmd identifyCmd = new IdentifyCmd(true);
			if(System.getProperty("os.name").startsWith("Win")){
				identifyCmd.setSearchPath(imageMagickPath);
			}
			ArrayListOutputConsumer output = new ArrayListOutputConsumer();
			identifyCmd.setOutputConsumer(output);
			identifyCmd.run(op, imagePath);
			ArrayList<String> cmdOutput = output.getOutput();
			assert cmdOutput.size() == 1;
			line = Integer.parseInt(cmdOutput.get(0));
		} catch (Exception e) {
			line = 0;
			e.printStackTrace();
			System.out.println("运行指令出错!");
		}
		return line;
	}

	/**
	 * 获得图片的高度
	 * 
	 * @param imagePath
	 *            文件路径
	 * @return 图片高度
	 */
	public int getHeight(String imagePath) {
		int line = 0;
		try {
			IMOperation op = new IMOperation();

			op.format("%h"); // 设置获取高度参数
			op.addImage(1);
			IdentifyCmd identifyCmd = new IdentifyCmd(true);
			if(System.getProperty("os.name").startsWith("Win")){
				identifyCmd.setSearchPath(imageMagickPath);
			}
			ArrayListOutputConsumer output = new ArrayListOutputConsumer();
			identifyCmd.setOutputConsumer(output);
			identifyCmd.run(op, imagePath);
			ArrayList<String> cmdOutput = output.getOutput();
			assert cmdOutput.size() == 1;
			line = Integer.parseInt(cmdOutput.get(0));
		} catch (Exception e) {
			line = 0;
			System.out.println("运行指令出错!" + e.toString());
		}
		return line;
	}

	/**
	 * 图片信息
	 * @param imagePath
	 * @return
	 */
	public static String getImageInfo(String imagePath) {
		String line = null;
		try {
			IMOperation op = new IMOperation();
			op.format("width:%w,height:%h,path:%d%f,size:%b%[EXIF:DateTimeOriginal]");
			op.addImage(1);
			IdentifyCmd identifyCmd = new IdentifyCmd(true);
			if(System.getProperty("os.name").startsWith("Win")){
				identifyCmd.setSearchPath(imageMagickPath);
			}
			
			ArrayListOutputConsumer output = new ArrayListOutputConsumer();
			identifyCmd.setOutputConsumer(output);
			identifyCmd.run(op, imagePath);
			ArrayList<String> cmdOutput = output.getOutput();
			assert cmdOutput.size() == 1;
			line = cmdOutput.get(0);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return line;
	}

	/**
	 * 裁剪图片
	 * @param imagePath 源图片路径
	 * @param newPath 处理后图片路径
	 * @param x 起始X坐标
	 * @param y 起始Y坐标
	 * @param width 裁剪宽度
	 * @param height 裁剪高度
	 * @return 返回true说明裁剪成功,否则失败
	 */
	public boolean cutImage(String imagePath, String newPath, int x, int y,
			int width, int height) {
		boolean flag = false;
		try {
			IMOperation op = new IMOperation();
			op.addImage(imagePath);
			/** width：裁剪的宽度 * height：裁剪的高度 * x：裁剪的横坐标 * y：裁剪纵坐标 */
			op.crop(width, height, x, y);
			op.addImage(newPath);
			ConvertCmd convert = new ConvertCmd(true);
			if(System.getProperty("os.name").startsWith("Win")){
				convert.setSearchPath(imageMagickPath);
			}
			convert.run(op);
			flag = true;
		} catch (IOException e) {
			System.out.println("文件读取错误!");
			flag = false;
		} catch (InterruptedException e) {
			flag = false;
		} catch (IM4JavaException e) {
			flag = false;
		} finally {

		}
		return flag;
	}

	/**
	 * 根据尺寸缩放图片[等比例缩放:参数height为null,按宽度缩放比例缩放;参数width为null,按高度缩放比例缩放]
	 * 
	 * @param imagePath 源图片路径
	 * @param newPath 处理后图片路径
	 * @param width 缩放后的图片宽度
	 * @param height 缩放后的图片高度
	 * @return 返回true说明缩放成功,否则失败
	 */
	public static boolean zoomImage(String imagePath, String newPath, Integer width, Integer height) {
		boolean flag = false;
		try {
			IMOperation op = new IMOperation();
			op.addImage(imagePath);
			if (width == null) {// 根据高度缩放图片
				op.addRawArgs("-resize", "x"+height.toString());
			} else if (height == null) {// 根据宽度缩放图片
				op.addRawArgs("-resize", width.toString());
			} else {
				op.addRawArgs("-resize", width.toString()+"x"+height.toString());
			}
			op.addRawArgs("-quality", "100");
			op.addRawArgs("-gravity", "Center");
			op.addImage(newPath);
			ConvertCmd convert = new ConvertCmd(true);
			if(System.getProperty("os.name").startsWith("Win")){
				convert.setSearchPath(imageMagickPath);
			}
			convert.run(op);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return flag;
	}

	/**
	 * 生成缩略图
	 * @param imagePath
	 * @param newPath
	 * @param width
	 * @param height
	 * @return
	 */
	public static boolean thumbnail(String imagePath, String newPath, Integer width, Integer height){
		boolean flag = false;
		if(width == null && height == null){
			return false;
		}
		try {
			IMOperation op = new IMOperation();
			op.addImage(imagePath);
			if (width == null) {// 根据高度缩放图片
				width = height;
			} else if (height == null) {// 根据宽度缩放图片
				height = width;
			}
			op.addRawArgs("-sample", width.toString()+"x"+height.toString()+"^");  
			op.addRawArgs("-extent", width.toString()+"x"+height.toString());  
			op.addRawArgs("-quality", "100");
			op.addRawArgs("-gravity", "Center");
			
			op.addImage(newPath);
			ConvertCmd convert = new ConvertCmd(true);
			convert.setSearchPath(imageMagickPath);
			convert.run(op);
			flag = true;
		} catch (IOException e) {
			System.out.println("文件读取错误!");
			flag = false;
		} catch (InterruptedException e) {
			flag = false;
		} catch (IM4JavaException e) {
			flag = false;
		} finally {

		}
		return flag;
	}
	
	/**
	 * 图片旋转
	 * 
	 * @param imagePath 源图片路径
	 * @param newPath 处理后图片路径
	 * @param degree 旋转角度
	 */
	public static boolean rotate(String imagePath, String newPath, double degree) {
		boolean flag = false;
		try {
			// 1.将角度转换到0-360度之间
			degree = degree % 360;
			if (degree <= 0) {
				degree = 360 + degree;
			}
			IMOperation op = new IMOperation();
			op.addImage(imagePath);
			op.addRawArgs("-rotate",String.valueOf(degree));
			op.addRawArgs("-quality", "100");
			op.addRawArgs("-gravity", "Center");
			op.addImage(newPath);
			ConvertCmd cmd = new ConvertCmd(true);
			cmd.setSearchPath(imageMagickPath);
			cmd.run(op);
			flag = true;
		} catch (Exception e) {
			flag = false;
			System.out.println("图片旋转失败!");
		}
		return flag;
	}

}