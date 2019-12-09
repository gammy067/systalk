package com.systalk.sys.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.systalk.sys.controller.base.BackendBaseContoller;
import com.systalk.sys.enums.Status;
import com.systalk.sys.service.BackendService;
import com.systalk.sys.service.BackendSettingService;
import com.systalk.sys.util.CommonUtil;
import com.systalk.sys.web.form.view.bean.BannerSettingBean;
import com.systalk.sys.web.form.view.bean.NewsAreaBean;
import com.systalk.sys.web.form.view.pageViewForm.backend.EditNewsAreaViewForm;
import com.systalk.sys.web.form.view.pageViewForm.backend.baseForm.UploadFileForm;

/**
 * 新聞專區編輯 Contoller.
 * @author Richard
 * 2019.06.06
 * */
@Controller
@RequestMapping("/backend")
public class BackendEditNewsAreaContoller extends BackendBaseContoller{

	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	BackendService backendService;

	@Autowired
	private BackendSettingService backendSettingService;

	/**
	 * 新聞專區 新增、編輯.
	 *
	 * @param request the request
	 * @param model the model
	 * @param seq the seq
	 * @return the model and view
	 * @throws Exception the exception
	 */
	@RequestMapping(value = {"/editNewsArea"}, method = { RequestMethod.POST})
	public ModelAndView editNewsArea(HttpServletRequest request, Model model, @RequestParam("seq") Integer seq) throws Exception {
		ModelAndView modelAndView = new ModelAndView("/backend/editNewsArea");
		EditNewsAreaViewForm viewForm = new EditNewsAreaViewForm();

		// 最新消息 初始分類下拉選單.
		viewForm.setTypeCombo(CommonUtil.getNewsTypeCombo(Locale.TAIWAN.toString()));
		// 英文分類下拉選單
		viewForm.setTypeComboEn(CommonUtil.getNewsTypeCombo(Locale.US.toString()));
		
		// 編輯
		if (seq != null) {
			NewsAreaBean newsAreaBean = backendService.queryNewsAreaBySeq(seq);
			// JEditorPane ed1 = new JEditorPane("text/html", newsAreaBean.getContent());
			
			// 設置上傳類型Form 共用方法.
			UploadFileForm uploadFileForm = super.setUploadFileForm(newsAreaBean);
			// html 資料結構處理
			uploadFileForm.setContent(CommonUtil.getHtmlContent(uploadFileForm.getContent()));
			uploadFileForm.setContentEn(CommonUtil.getHtmlContent(uploadFileForm.getContentEn()));
			
			// 圖片檔案路徑.
			viewForm.setImageUrl(newsAreaBean.getUrl());
			viewForm.setUploadFileForm(uploadFileForm);
			viewForm.setTypeSeq(newsAreaBean.getTypeSeq());
			viewForm.setTypeSeqEn(newsAreaBean.getTypeSeqEn());
		}
		
		modelAndView.addObject("viewForm", viewForm);
		return modelAndView;
	}
	
//	//用ajax方式取得文章內容，這是為了防止文章內容破壞html結構.
//	@RequestMapping(value = {"/getEditorContent"},
//			method=RequestMethod.POST, 
//			produces = {MediaType.APPLICATION_JSON_VALUE}, 
//			consumes = {MediaType.APPLICATION_JSON_VALUE})
//	@ResponseBody
//	public SettingForm getEditorContent(Model model, HttpSession session, HttpServletRequest request,
//			@RequestBody SettingForm settingForm) throws Exception{
//		
//		if (settingForm != null && settingForm.getSeq() != null) {
//			NewsAreaBean newsAreaBean = backendService.queryNewsAreaBySeq(settingForm.getSeq());
//			settingForm = new SettingForm();
//			settingForm.setContent(newsAreaBean.getContent());
//		} else {
//			settingForm = new SettingForm();
//		}
//
//		return settingForm;
//	}

	/**
	 * 新聞專區編輯儲存.
	 *
	 * @param newsSeq the news seq
	 * @param newsTitle the news title
	 * @param status the status
	 * @param content the content
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value = { "/doNewsAreaSaveAction" }, method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView doNewsAreaSaveAction(Model model,
			@Valid @ModelAttribute("viewForm") EditNewsAreaViewForm viewForm,
			BindingResult bindingResult) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		logger.info("doNewsAreaSaveAction()");

		// 選擇指定日期: 上架/下架日期驗證.
		if(Status.ASSIGN.getCode().equals(viewForm.getUploadFileForm().getStatus())) {
			validateDate(viewForm.getUploadFileForm(), bindingResult, "uploadFileForm.pushingDate" ,"uploadFileForm.invalidDate");
		}

		// 欄位驗證
		if (bindingResult.hasErrors()) {
			// 初始分類下拉選單.
			viewForm.setTypeCombo(CommonUtil.getNewsTypeCombo(Locale.TAIWAN.toString()));
			// 英文分類下拉選單
			viewForm.setTypeComboEn(CommonUtil.getNewsTypeCombo(Locale.US.toString()));

			// 新增時 檢核失敗圖片清空, 再重新選擇圖片
			if(viewForm.getUploadFileForm().getSeq() == null) {
				viewForm.setImageUrl("");
			}
			
			// html 資料結構處理
			String content = viewForm.getUploadFileForm().getContent();
			viewForm.getUploadFileForm().setContent(CommonUtil.getHtmlContent(content));
			
			String contentEn = viewForm.getUploadFileForm().getContentEn();
			viewForm.getUploadFileForm().setContentEn(CommonUtil.getHtmlContent(contentEn));

			modelAndView.setViewName("/backend/editNewsArea");
			return modelAndView;
		}

		backendSettingService.doNewsAreaSaveAction(viewForm, getLoginUser());
		
		// 設置新增或編輯訊息
		// setAddOrModifySuccessMsg(viewForm.getUploadFileForm().getSeq() == null ? true :false, "新聞專區");

        return new ModelAndView("redirect:/backend/newsAreaHome");
	}
}
