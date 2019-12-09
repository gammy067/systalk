package com.systalk.sys.controller.base;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.systalk.sys.util.MessageUtil;

/**
 * 後臺登入Base Contoller.
 * @author Richard
 * 2019.06.06
 * */
public class FrontendBaseContoller {

	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	protected HttpServletRequest request;
	
	@Autowired
	protected MessageUtil messageUtil;

	
	public static String getAjaxSuccessJson() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isSuccess", true);
		return mapper.writeValueAsString(map);
	}
}
