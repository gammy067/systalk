package com.systalk.sys.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.systalk.sys.controller.base.BackendBaseContoller;
import com.systalk.sys.controller.validator.LogoWallValidator;
import com.systalk.sys.enums.Status;
import com.systalk.sys.service.BackendService;
import com.systalk.sys.service.BackendSettingService;
import com.systalk.sys.util.MessageUtil;
import com.systalk.sys.web.form.view.bean.LogoWallBean;
import com.systalk.sys.web.form.view.pageViewForm.backend.EditLogoWallSortViewForm;
import com.systalk.sys.web.form.view.pageViewForm.backend.EditLogoWallViewForm;
import com.systalk.sys.web.form.view.pageViewForm.backend.baseForm.UploadFileForm;

/**
 * 品牌牆新增、編輯 Contoller.
 * @author Richard
 * */
@Controller
@RequestMapping("/backend")
public class BackendEditLogoWallContoller extends BackendBaseContoller{

	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	BackendService backendService;

	@Autowired
	MessageUtil messageUtil;
	
	@Autowired
	private BackendSettingService backendSettingService;

	/**
	 * 品牌牆新增、編輯.
	 *
	 * @param request the request
	 * @param model the model
	 * @param seq the seq
	 * @return the model and view
	 * @throws Exception the exception
	 */
	@RequestMapping(value = {"/editLogoWall"}, method = { RequestMethod.POST})
	public ModelAndView editLogoWall(HttpServletRequest request, Model model, @RequestParam("seq") Integer seq) throws Exception {
		ModelAndView modelAndView = new ModelAndView("/backend/editLogoWall");
		EditLogoWallViewForm viewForm = new EditLogoWallViewForm();

		// 編輯
		if (seq != null) {
			LogoWallBean logoWallBean = backendService.queryLogoWallBySeq(seq);
			
			UploadFileForm uploadFileForm = new UploadFileForm();
			uploadFileForm.setSeq(logoWallBean.getSeq());
			uploadFileForm.setPushingDate(logoWallBean.getPushingDate().toString());
			uploadFileForm.setStatus(logoWallBean.getStatus());
			uploadFileForm.setFileName(logoWallBean.getFileName());

			viewForm.setImageUrl(logoWallBean.getUrl());
			viewForm.setUploadFileForm(uploadFileForm);
		}
		modelAndView.addObject("viewForm", viewForm);
		return modelAndView;
	}
	
	/**
	 * 品牌牆編輯儲存.
	 *
	 * @param newsSeq the news seq
	 * @param newsTitle the news title
	 * @param status the status
	 * @param content the content
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value = { "/doLogoWallSaveAction" }, method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView doLogoWallSaveAction(HttpServletRequest request,
			@ModelAttribute("viewForm") EditLogoWallViewForm viewForm, 
			BindingResult result) throws Exception {

		logger.info("doLogoWallSaveAction()");
		
		// 欄位驗證
		LogoWallValidator validator = new LogoWallValidator();
		validator.validate(viewForm, result);
		if (result.hasErrors()) {

			// 新增時 檢核失敗圖片清空, 再重新選擇圖片
			if(viewForm.getUploadFileForm().getSeq() == null) {
				viewForm.setImageUrl("");
			}

			return new ModelAndView("/backend/editLogoWall");
		}

		backendSettingService.doSaveLogoWallAction(viewForm.getUploadFileForm(), getLoginUser());

		// 設置新增或編輯訊息
		// setAddOrModifySuccessMsg(viewForm.getUploadFileForm().getSeq() == null ? true :false, "品牌牆");

		return new ModelAndView("redirect:/backend/logoWallHome");
	}

	/**
	 * 品牌牆排序編輯.
	 *
	 * @param request the request
	 * @param model the model
	 * @param seq the seq
	 * @return the model and view
	 * @throws Exception the exception
	 */
	@RequestMapping(value = {"/editLogoWallSort"}, method = { RequestMethod.POST, RequestMethod.GET})
	public ModelAndView editLogoWallSort(HttpServletRequest request, Model model) throws Exception {
		ModelAndView modelAndView = new ModelAndView("/backend/editLogoWallSort");
		EditLogoWallSortViewForm viewForm = new EditLogoWallSortViewForm();

		Sort sort = new Sort(Sort.Direction.ASC, "logoOrder");
		
		List<LogoWallBean> logoWallList = new LinkedList<LogoWallBean>();
		logoWallList.addAll(backendService.queryLogoWallBySort(sort));

		viewForm.setLogoWallList(logoWallList);
		modelAndView.addObject("viewForm", viewForm);
		return modelAndView;
	}

	/**
	 * logo牆排序 後臺管理 儲存.
	 *
	 * @param model the model
	 * @param session the session
	 * @param request the request
	 * @param seqSortList the seq sort list
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value = {"/doLogoWallSortSaveAction"},method=RequestMethod.POST)
	@ResponseBody
	public String doLogoWallSortSaveAction(Model model, HttpSession session, HttpServletRequest request,
			@RequestBody List<Integer> seqSortList) throws Exception{
		
		backendSettingService.doSaveLogowallSortAction(seqSortList, getLoginUser());

		return getAjaxSuccessJson();
	}

}
