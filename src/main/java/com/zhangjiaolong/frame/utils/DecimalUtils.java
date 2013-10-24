package com.zhangjiaolong.frame.utils;

import java.math.BigDecimal;

public class DecimalUtils {

	// 默认除法运算精度
	private static final int DEF_DIV_SCALE = 10;

	/**
	 * 提供精确的加法运算。
	 * @param one 被加数
	 * @param two 加数
	 * @return 两个参数的和
	 */
	public static double add(double one, double two) {
		BigDecimal b1 = new BigDecimal(Double.toString(one));
		BigDecimal b2 = new BigDecimal(Double.toString(two));
		return b1.add(b2).doubleValue();
	}

	/**
	 * 提供精确的减法运算。
	 * @param one 被减数
	 * @param two  减数
	 * @return 两个参数的差
	 */
	public static double sub(double one, double two) {
		BigDecimal b1 = new BigDecimal(Double.toString(one));
		BigDecimal b2 = new BigDecimal(Double.toString(two));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 提供精确的乘法运算。
	 * @param one 被乘数
	 * @param two 乘数
	 * @return 两个参数的积
	 */
	public static double mul(double one, double two) {
		BigDecimal b1 = new BigDecimal(Double.toString(one));
		BigDecimal b2 = new BigDecimal(Double.toString(two));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
	 * @param one 被除数
	 * @param two 除数
	 * @return 两个参数的商
	 */
	public static double div(double one, double two) {
		return div(one, two, DEF_DIV_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * @param one 被除数
	 * @param two  除数
	 * @param scale 表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double div(double one, double two, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(one));
		BigDecimal b2 = new BigDecimal(Double.toString(two));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * @param decimal 需要四舍五入的数字
	 * @param scale 小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double decimal, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(decimal));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

}
