package com.systalk.sys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
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
import com.systalk.sys.web.form.view.bean.LogoWallBean;
import com.systalk.sys.web.form.view.bean.VideoSettingBean;
import com.systalk.sys.web.form.view.pageViewForm.backend.EditVideoViewForm;
import com.systalk.sys.web.form.view.pageViewForm.backend.baseForm.SettingForm;
import com.systalk.sys.web.form.view.pageViewForm.backend.baseForm.UploadFileForm;

/**
 * 行銷影片編輯 Contoller.
 * @author Richard
 * */
@Controller
@RequestMapping("/backend")
public class BackendEditVideoContoller extends BackendBaseContoller {

	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	BackendService backendService;
	
	@Autowired
	private BackendSettingService backendSettingService;

	@RequestMapping(value = {"/editVideo"}, method = { RequestMethod.POST})
	public ModelAndView editVideo(HttpServletRequest request, Model model, @RequestParam(value = "seq", required = false) Integer seq) throws Exception {
		ModelAndView modelAndView = new ModelAndView("/backend/editVideo");
		EditVideoViewForm viewForm = new EditVideoViewForm();

		// 編輯
		if (seq != null) {
			VideoSettingBean videoBean = backendService.queryVideoSettingBySeq(seq);
			
			// 設置一般類型Form 共用方法.
			SettingForm settingForm = super.setSettingForm(videoBean);
			
			// html 資料結構處理
			//settingForm.setContent(CommonUtil.getHtmlContent(settingForm.getContent()));
			//settingForm.setUrl(CommonUtil.getHtmlContent(settingForm.getUrl()));

			viewForm.setSettingForm(settingForm);
		}
		modelAndView.addObject("viewForm", viewForm);
		return modelAndView;
	}

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
	@RequestMapping(value = { "/doVideoSaveAction" }, method = { RequestMethod.POST})
	public ModelAndView doVideoSaveAction(
			@Valid @ModelAttribute("viewForm") EditVideoViewForm viewForm,
			BindingResult bindingResult) throws Exception {
		ModelAndView modelAndView = new ModelAndView();

		logger.info("doVideoSaveAction()");
		
		// 驗證: 嵌入式影片 url必填
		ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "settingForm.url", "", "欄位必填");
		
		// 欄位驗證
		if (bindingResult.hasErrors()) {
			// html 資料結構處理
//			String content = viewForm.getSettingForm().getContent();
			//viewForm.getSettingForm().setContent(CommonUtil.getHtmlContent(content));
			
			// html 資料結構處理
//			String url = viewForm.getSettingForm().getUrl();
			//viewForm.getSettingForm().setUrl(CommonUtil.getHtmlContent(url));

			return new ModelAndView("/backend/editVideo");
		}
		
		// 嵌入式影片 加入https: 字首.
//		if(StringUtils.isNotEmpty(viewForm.getSettingForm().getUrl())) {
//			viewForm.getSettingForm().setUrl(CommonUtil.videoUrlAddHttpPrefix(viewForm.getSettingForm().getUrl()));
//		}

		backendSettingService.doVideoSaveAction(viewForm.getSettingForm(), getLoginUser());
		
		// 設置新增或編輯訊息
		// setAddOrModifySuccessMsg(viewForm.getSettingForm().getSeq() == null ? true :false, "行銷影片");

        return new ModelAndView("redirect:/backend/videoHome");
	}
}
