package com.systalk.sys.service;

import com.systalk.sys.web.form.view.pageViewForm.frontend.FrontendNewsAreaForm;

/**
 * 前臺 新聞專區 Service interface.
 * @author Richard
 * 2019.06.06
 * */
public interface FrontendNewsAreaService {
	
	/** 取得新聞專區 初始新聞列表(分頁物件). 
	 * @throws Exception */
	public FrontendNewsAreaForm initNewsAreaList(FrontendNewsAreaForm viewForm, int startPage) throws Exception;
	
	/** 最新消息查詢 viewForm. 
	 * @throws Exception */
	public FrontendNewsAreaForm queryNewsAreaByCondition(FrontendNewsAreaForm viewForm, int startPage) throws Exception;
	
}
