package com.systalk.sys.util;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * MapUtil.
 * @author Richard
 */
public class MapUtil {

	/** The mapper. */
	private static ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * Object to map.
	 *
	 * @param obj the obj
	 * @return the map
	 */
	public static Map<String, Object> objectToMap(Object obj){
		mapper = new ObjectMapper();
		return mapper.convertValue(obj, Map.class);
	}

	/**
	 * Map to object.
	 *
	 * @param <T> the generic type
	 * @param obj the obj
	 * @param clazz the clazz
	 * @return the t
	 */
	public static <T> T mapToObject(Object obj, Class<T> clazz){
		mapper = new ObjectMapper();
		return  mapper.convertValue(obj, clazz);
	}
}
