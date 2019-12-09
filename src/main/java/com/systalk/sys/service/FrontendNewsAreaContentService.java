package com.systalk.sys.service;

import com.systalk.sys.web.form.view.pageViewForm.frontend.FrontendNewsAreaContentForm;

/**
 * 前臺 最新消息內文 Service interface.
 * @author Richard
 * */
public interface FrontendNewsAreaContentService {
	
	/** 取得最新消息內文 viewForm. 
	 * @throws Exception */
	public FrontendNewsAreaContentForm getNewsAreaContentForm(Integer seq) throws Exception;

	
}
