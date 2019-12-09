package com.systalk.sys.controller.base;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.util.WebUtils;

import com.systalk.sys.controller.exception.StRuntimeException;
import com.systalk.sys.controller.validator.common.DateValidator;
import com.systalk.sys.model.User;
import com.systalk.sys.util.MessageUtil;
import com.systalk.sys.web.form.view.pageViewForm.backend.baseForm.SettingForm;
import com.systalk.sys.web.form.view.pageViewForm.backend.baseForm.UploadFileForm;

/**
 * 後臺登入Base Contoller.
 * @author Richard
 * 2019.06.06
 * */
public class BackendBaseContoller {

	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	protected MessageUtil messageUtil;
	
	/**
	 *  取得當前登入者資訊
	 *
	 * @param request the request
	 * @return the login user
	 * @throws ServletException 
	 * @throws IOException 
	 */
    @ModelAttribute("loginUser")
	protected final User getLoginUser() throws IOException, ServletException {
    	User loginUser = (User) WebUtils.getSessionAttribute(request, "loginUser");
    	// 判斷已登入使用者 操作是否逾時
    	if(!StringUtils.contains(request.getRequestURI(), "/login")) {
        	if(request.getUserPrincipal() != null) {
        		if(loginUser == null) {
        			throw new UsernameNotFoundException("loginUser is null ");
        		}
        	}
    	}

		//HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return loginUser;
	}
	
	protected static String getAjaxSuccessJson() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isSuccess", true);
		return mapper.writeValueAsString(map);
	}
	
	/**
	 * 設置一般類型Form 共用方法.
	 *
	 * @param bean the bean
	 * @return the upload file form
	 * @throws StRuntimeException the st runtime exception
	 */
	protected SettingForm setSettingForm(Object bean) throws StRuntimeException {
		SettingForm settingForm = new SettingForm();
		try {
			BeanUtils.copyProperties(settingForm, bean);

		} catch (IllegalAccessException e) {
			throw new StRuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new StRuntimeException(e);
		}
		return settingForm;
	}
	
	/**
	 * 設置上傳類型Form 共用方法.
	 *
	 * @param bean the bean
	 * @return the upload file form
	 * @throws StRuntimeException the st runtime exception
	 */
	protected UploadFileForm setUploadFileForm(Object bean) throws StRuntimeException {
		UploadFileForm uploadFileForm = new UploadFileForm();
		try {
			BeanUtils.copyProperties(uploadFileForm, bean);

		} catch (IllegalAccessException e) {
			throw new StRuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new StRuntimeException(e);
		}
		return uploadFileForm;
	}
	
	/**
	 * 設置新增或編輯訊息.
	 *
	 * @param isNew the new adds the or modify success msg
	 */
	protected void setAddOrModifySuccessMsg(boolean isNew, String itemName) {
		HttpSession session = request.getSession();
		// msg 新增成功!
		if(isNew) {
			session.setAttribute("msg", messageUtil.getMessage("backend.add.success", new String[] {itemName}));
		} else {
		// msg 編輯成功!
			session.setAttribute("msg", messageUtil.getMessage("backend.modify.success", new String[] {itemName}));
		}
		
//		#後臺資料異動訊息
//		backend.add.success={0}新增成功!
//		backend.add.faile={0}新增失敗!
//		backend.modify.success={0}編輯成功!
//		backend.modify.faile={0}編輯失敗!
	}
	
	/**
	 * 上架/下架日期驗證.
	 *
	 * @param uploadFileForm the upload file form
	 * @param result the result
	 * @param fieldNames the field names
	 */
	protected void validateDate(UploadFileForm uploadFileForm, BindingResult result, String... fieldNames) {
		DateValidator dateValidator = new DateValidator();
		dateValidator.validate(uploadFileForm.getPushingDate(), uploadFileForm.getInvalidDate(), result, fieldNames);
	}
}
