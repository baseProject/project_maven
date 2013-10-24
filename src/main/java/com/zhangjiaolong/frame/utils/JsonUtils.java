package com.zhangjiaolong.frame.utils;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtils {
	
	private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);
	
	/**
	 * 将bean、List、Map、Array转成Json字符串
	 * @param obj bean、List、Map、Array
	 * @return json 字符串
	 */
	public static String objToString(Object obj){
		String json = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			json = mapper.writeValueAsString(obj);
		} catch (Exception e) {
			logger.error("生成JSON字符串出错"+obj.getClass().getName(),e);
			json = "";
		}
		return json;
	}
	
	/**
	 * 将转成Json字符串转换成相应的Class对象
	 * @param json
	 * @param clazz
	 */
	public static <T> T stringToObj(String json, Class<T> clazz){
		ObjectMapper mapper = new ObjectMapper();
		JsonFactory factory = new JsonFactory(mapper);
		mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
		try {
			JsonParser jsonParser = factory.createJsonParser(json);
			return jsonParser.readValueAs(clazz);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
}
