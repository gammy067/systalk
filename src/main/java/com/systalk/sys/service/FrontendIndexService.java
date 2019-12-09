package com.systalk.sys.service;

import com.systalk.sys.web.form.view.pageViewForm.frontend.FrontendIndexForm;

/**
 * 前臺首頁 Service interface.
 * @author Richard
 * 2019.06.06
 * */
public interface FrontendIndexService {

	/** 取得首頁顯示 viewForm. 
	 * @throws Exception */
	public FrontendIndexForm initIndexForm() throws Exception;
}
