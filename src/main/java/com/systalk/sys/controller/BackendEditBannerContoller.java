package com.systalk.sys.controller;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
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
import com.systalk.sys.controller.validator.common.DateValidator;
import com.systalk.sys.enums.Status;
import com.systalk.sys.service.BackendService;
import com.systalk.sys.service.BackendSettingService;
import com.systalk.sys.util.CommonUtil;
import com.systalk.sys.web.form.view.bean.BannerSettingBean;
import com.systalk.sys.web.form.view.pageViewForm.backend.EditBannerViewForm;
import com.systalk.sys.web.form.view.pageViewForm.backend.baseForm.UploadFileForm;

/**
 * 後臺首頁 設定Contoller.
 * 
 * @author Richard
 */
@Controller
@RequestMapping("/backend")
@MultipartConfig
public class BackendEditBannerContoller extends BackendBaseContoller {

	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private BackendService backendService;

	@Autowired
	private BackendSettingService backendSettingService;

	/**
	 * 首頁輪播設定 新增、編輯.
	 *
	 * @param request the request
	 * @return the model and view
	 * @throws Exception the exception
	 */
	@RequestMapping(value = { "/editBanner" }, method = { RequestMethod.POST })
	public ModelAndView editBanner(HttpServletRequest request, Model model, @RequestParam("seq") Integer seq)
			throws Exception {
		ModelAndView modelAndView = new ModelAndView("/backend/editBanner");
		EditBannerViewForm viewForm = new EditBannerViewForm();

		// 編輯
		if (seq != null) {
			BannerSettingBean bannerSettingBean = backendService.queryBannerSettingBySeq(seq);

			// 設置上傳form.
			// UploadFileForm uploadFileForm = setBannerUploadFileForm(bannerSettingBean);
			
			// 設置上傳類型Form 共用方法
			UploadFileForm uploadFileForm = super.setUploadFileForm(bannerSettingBean);

			// 一般 web 圖片格式 檔案路徑.
			viewForm.setImageUrl(bannerSettingBean.getUrl());
			// mobile圖片格式 檔案路徑.
			viewForm.setImageUrl2(bannerSettingBean.getUrl2());
			viewForm.setUploadFileForm(uploadFileForm);
		}
		
		modelAndView.addObject("viewForm", viewForm);

		return modelAndView;
	}
	
	/**
	 * 設置上傳form.
	 *
	 * @param bannerSettingBean the banner setting bean
	 * @return the upload file form
	 */
	private UploadFileForm setBannerUploadFileForm(BannerSettingBean bannerSettingBean) {
		UploadFileForm uploadFileForm = new UploadFileForm();
		uploadFileForm.setSeq(bannerSettingBean.getSeq());
		uploadFileForm.setTitle(bannerSettingBean.getTitle());
		uploadFileForm.setTitleEn(bannerSettingBean.getTitleEn());
		
		// 上架日期字串格式 (非必填)
		uploadFileForm.setPushingDate(CommonUtil.getCommonDateStr(bannerSettingBean.getPushingDate()));
		// 下架日期字串格式 (非必填)
		uploadFileForm.setInvalidDate(CommonUtil.getCommonDateStr(bannerSettingBean.getInvalidDate()));
		
		uploadFileForm.setStatus(bannerSettingBean.getStatus());
		uploadFileForm.setFileName(bannerSettingBean.getFileName());
		uploadFileForm.setFileName2(bannerSettingBean.getFileName2());
		uploadFileForm.setContent(bannerSettingBean.getContent());
		uploadFileForm.setContentEn(bannerSettingBean.getContentEn());
		return uploadFileForm;
	}

	/**
	 * 首頁輪播設定 儲存.
	 *
	 * @param model    the model
	 * @param request  the request
	 * @param fileName the file name
	 * @param title    the title
	 * @param content  the content
	 * @return the model and view
	 * @throws Exception the exception
	 */
	@RequestMapping(value = { "/doBannerSettingSaveAction" }, method = { RequestMethod.POST })
	public ModelAndView doBannerSettingSaveAction(Model model,
			@Valid  @ModelAttribute("viewForm") EditBannerViewForm viewForm,
			BindingResult bindingResult) throws Exception {
		logger.debug("doBannerSettingSaveAction(");
		
		// 選擇指定日期: 上架/下架日期驗證.
		if(Status.ASSIGN.getCode().equals(viewForm.getUploadFileForm().getStatus())) {
			validateDate(viewForm.getUploadFileForm(), bindingResult, "uploadFileForm.pushingDate" ,"uploadFileForm.invalidDate");
		}
		
		if(viewForm.getUploadFileForm().getSeq() == null) {
			// 新增 - 圖片需選擇檔案
			ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "uploadFileForm.fileName", "", "欄位必填");
		}
		
		// 欄位驗證
		if (bindingResult.hasErrors()) {
			// 新增時 檢核失敗圖片清空, 再重新選擇圖片
			if(viewForm.getUploadFileForm().getSeq() == null) {
				viewForm.setImageUrl("");
			}		
			
			return new ModelAndView("/backend/editBanner");
		}

		backendSettingService.doBannerSettingSaveAction(viewForm.getUploadFileForm(), getLoginUser());
		
		// 設置新增或編輯訊息
		//setAddOrModifySuccessMsg(viewForm.getUploadFileForm().getSeq() == null ? true :false, "首頁輪播圖");

        return new ModelAndView("redirect:/backend/bannerHome");
	}
}
