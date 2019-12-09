package com.systalk.sys.controller;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.UriUtils;

import com.systalk.sys.controller.base.BackendBaseContoller;
import com.systalk.sys.service.BackendSettingService;

/**
 * 後臺維護 編輯器Contoller.
 * 
 * @author Richard
 */
@Controller
@RequestMapping("/backend")
public class BackendEditorContoller extends BackendBaseContoller {

	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BackendSettingService backendSettingService;

	/**
	 * 編輯器圖片上傳.
	 *
	 * @param session the session
	 * @param typeSeq the type seq
	 * @param file the file
	 * @param type the type
	 * @param request the request
	 * @return the string
	 * @throws Exception 
	 */
	@RequestMapping(value = { "/doUploadEditImageAction" }, method = RequestMethod.POST)
	@ResponseBody
	public String doUploadEditImageAction(HttpSession session,
			@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "type", required = false) String type, 
			MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("type:" + type);

		String url = backendSettingService.doEditorUploadImageAction(file, type, request);

		// 處理中文導致亂碼
		return UriUtils.encode(url, StandardCharsets.UTF_8);
	}
}
