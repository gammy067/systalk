package com.systalk.sys.util;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtil {

	private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static <T> T ConvertEntity(Object from, Class<T> type){
		String jsonString = toJsonString(from);
		return  toEntity(jsonString, type);
	}
	
	public static String toJsonString(Object value){
		String jsonString = null;
		
		try {
				jsonString = mapper.writeValueAsString(value);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("toJsonString", e.fillInStackTrace());
		}finally{
			logger.info("from:" + jsonString);
			return jsonString;
		}
	}

	public static <T> T toEntity(String jsonString, Class<T> type){
		T entity = null;
		try {
			entity = mapper.readValue(jsonString, type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("toEntity", e.fillInStackTrace());
		}finally{
			return entity;
		}
	}
}
