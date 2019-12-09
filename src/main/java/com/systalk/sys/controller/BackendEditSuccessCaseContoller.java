package com.systalk.sys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringEscapeUtils;
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
import com.systalk.sys.controller.validator.LogoWallValidator;
import com.systalk.sys.controller.validator.SuccessCaseValidator;
import com.systalk.sys.enums.Status;
import com.systalk.sys.enums.SuccessCaseStatus;
import com.systalk.sys.model.User;
import com.systalk.sys.service.BackendService;
import com.systalk.sys.service.BackendSettingService;
import com.systalk.sys.util.CommonUtil;
import com.systalk.sys.web.form.view.bean.BannerSettingBean;
import com.systalk.sys.web.form.view.bean.SuccessCaseBean;
import com.systalk.sys.web.form.view.pageViewForm.backend.EditSuccessCaseViewForm;
import com.systalk.sys.web.form.view.pageViewForm.backend.baseForm.UploadFileForm;

/**
 * 成功案例 設定Contoller.
 * 
 * @author Richard
 */
@Controller
@RequestMapping("/backend")
public class BackendEditSuccessCaseContoller extends BackendBaseContoller {

	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private BackendService backendService;

	@Autowired
	private BackendSettingService backendSettingService;

	/**
	 * 成功案例設定 新增、編輯.
	 *
	 * @param request the request
	 * @return the model and view
	 * @throws Exception the exception
	 */
	@RequestMapping(value = { "/editSuccessCase" }, method = { RequestMethod.POST })
	public ModelAndView editSuccessCase(HttpServletRequest request, Model model, @RequestParam(value = "seq", required = false) Integer seq)
			throws Exception {
		ModelAndView modelAndView = new ModelAndView("/backend/editSuccessCase");
		EditSuccessCaseViewForm viewForm = new EditSuccessCaseViewForm();

		// 編輯
		if (seq != null) {
			SuccessCaseBean successCaseBean = backendService.querySuccessCaseBySeq(seq);

			// 設置上傳類型Form 共用方法
			UploadFileForm uploadFileForm = super.setUploadFileForm(successCaseBean);
			
			// html 資料結構處理
			// uploadFileForm.setContent(CommonUtil.getHtmlContent(successCaseBean.getContent()));
			
			viewForm.setImageUrl(successCaseBean.getUrl());
			viewForm.setUploadFileForm(uploadFileForm);
			
			// 設置上架狀態 : 勾選 -> 1(立即上架), 沒勾選 -> 0 (下架) 
			String effCode = SuccessCaseStatus.EFFECTIVE.getCode();
			viewForm.setStatusCheckVal(successCaseBean.getStatus().equals(effCode) ? true : false);
		}
		modelAndView.addObject("viewForm", viewForm);

		return modelAndView;
	}

	/**
	 * 成功案例設定 儲存.
	 *
	 * @param model    the model
	 * @param request  the request
	 * @param fileName the file name
	 * @param title    the title
	 * @param content  the content
	 * @return the model and view
	 * @throws Exception the exception
	 */
	@RequestMapping(value = { "/doSuccessCaseSaveAction" }, method = { RequestMethod.POST })
	public ModelAndView doSuccessCaseSaveAction(Model model,
			@ModelAttribute("viewForm") EditSuccessCaseViewForm viewForm,
			BindingResult result) throws Exception {
		logger.debug("doSuccessCaseSettingSaveAction(");

		// 客製 - 欄位驗證
		SuccessCaseValidator validator = new SuccessCaseValidator();
		validator.validate(viewForm, result);
		if (result.hasErrors()) {
			
			// 新增時 檢核失敗圖片清空, 再重新選擇圖片
			if(viewForm.getUploadFileForm().getSeq() == null) {
				viewForm.setImageUrl("");
			}
			
			// html 資料結構處理
			//String content = viewForm.getUploadFileForm().getContent();
			//viewForm.getUploadFileForm().setContent(CommonUtil.getHtmlContent(content));
			
			return new ModelAndView("/backend/editSuccessCase");
		}

		// 設置上架狀態 : 勾選 -> 1(立即上架), 沒勾選 -> 0 (下架) 
		viewForm.getUploadFileForm().setStatus(viewForm.isStatusCheckVal() ? "1" : "0");

		backendSettingService.doSuccessCaseSaveAction(viewForm.getUploadFileForm(), getLoginUser());
		
		// 設置新增或編輯訊息
		// setAddOrModifySuccessMsg(viewForm.getUploadFileForm().getSeq() == null ? true :false, "成功案例");

        return new ModelAndView("redirect:/backend/successCaseHome");
	}
}
