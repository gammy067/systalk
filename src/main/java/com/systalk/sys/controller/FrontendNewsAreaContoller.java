package com.systalk.sys.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.systalk.sys.controller.base.FrontendBaseContoller;
import com.systalk.sys.service.FrontendNewsAreaService;
import com.systalk.sys.web.form.view.pageViewForm.frontend.FrontendNewsAreaForm;

/**
 * 新聞專區 Contoller.
 * @author Richard
 * */
@Controller
public class FrontendNewsAreaContoller extends FrontendBaseContoller {

	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/** The frontend index service. */
	@Autowired
	FrontendNewsAreaService frontendNewsAreaService;

	/**
	 * 新聞專區 NewsArea.
	 *
	 * @param model the model
	 * @param principal the principal
	 * @param request the request
	 * @return the string
	 * @throws Exception the exception
	 */
    @RequestMapping(value = {"/newsArea"},  method = {RequestMethod.POST, RequestMethod.GET })
	public ModelAndView newsArea(Model model) throws Exception {
		logger.info("NewsArea()");

		// 分頁起始頁面
		int startPage = 1;
		FrontendNewsAreaForm viewForm = new FrontendNewsAreaForm();
		// 頁面初始 最新消息清單 分頁處理 (查找全部, 上架)
		frontendNewsAreaService.initNewsAreaList(viewForm, startPage);
		
		model.addAttribute("viewForm", viewForm);

		return new ModelAndView("/newsArea");
	}

	/**
	 * 最新消息 搜尋.
	 * 分類 + 年分 + 關鍵字
	 * 
	 * @param model the model
	 * @param principal the principal
	 * @param request the request
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value = {"/newsArea/doQueryNewsAction"}, method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView doQueryNewsAction(Model model,
			@ModelAttribute("viewForm") FrontendNewsAreaForm viewForm
			) throws Exception {
		logger.info("doQueryNewsAction()");

		// 分頁起始頁面
		int startPage = 1;
		// 最新消息查詢結果
		viewForm = frontendNewsAreaService.queryNewsAreaByCondition(viewForm, startPage);

		model.addAttribute("viewForm", viewForm);

		return new ModelAndView("/newsArea");
	}
	
    /**
         *  分頁處理顯示當前頁面資料.
     *
     * @param viewForm the view form
     * @return the frontend news area form
     * @throws Exception the exception
     */
    @RequestMapping(value = {"/newsArea/doNewsAreaListCurrentPageAction"},  
    method = {RequestMethod.POST }, 
	produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody FrontendNewsAreaForm doNewsAreaListCurrentPageAction(
			Model model,
			@ModelAttribute FrontendNewsAreaForm viewForm) throws Exception {
		
		logger.info(">>> /newsArea/doNewsAreaListCurrentPageAction");
		
		// 分頁起始頁面
		int startPage = viewForm.getPageObject().getStartPage();
		if(!isPageableByQuery(viewForm)) {
			// 最新消息清單 分頁處理 (查找全部, 上架)
			frontendNewsAreaService.initNewsAreaList(viewForm, startPage);
		} else {
			// 最新消息查詢結果 分頁處理
			frontendNewsAreaService.queryNewsAreaByCondition(viewForm, startPage);
		}

		return viewForm;
	}
    
    /**
     * 分頁是否依據查詢條件.
     *
     * @param viewForm the view form
     * @return true, if is pageable by query
     */
    private boolean isPageableByQuery(FrontendNewsAreaForm viewForm) {
    	boolean isByQuery = false;
    	if(viewForm.getTypeSeq() != null) {
    		isByQuery = true;
    	}
    	return isByQuery;
    }
}
