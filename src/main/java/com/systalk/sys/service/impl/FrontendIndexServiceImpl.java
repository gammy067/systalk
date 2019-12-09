package com.systalk.sys.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.systalk.sys.service.FrontendIndexService;
import com.systalk.sys.service.impl.helper.FrontendIndexServiceHelper;
import com.systalk.sys.util.CommonUtil;
import com.systalk.sys.web.form.view.pageViewForm.frontend.FrontendIndexForm;

/**
 * 首頁serviceImpl.
 */
@Transactional(rollbackFor = Exception.class)
@Service("FrontendIndexService")
public class FrontendIndexServiceImpl implements FrontendIndexService {
	
	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
    private HttpServletRequest request;
	
	/**
	 *  取得首頁顯示 viewForm. 
	 *
	 * @return the frontend index form
	 * @throws Exception the exception
	 */
	@Override
	public FrontendIndexForm initIndexForm() throws Exception {
		FrontendIndexServiceHelper helper = FrontendIndexServiceHelper.getInstance();

		// 取得首頁資料
		FrontendIndexForm viewForm = helper.getFrontendIndexForm(CommonUtil.getBaseURL(request));
		return viewForm;
	}
}
