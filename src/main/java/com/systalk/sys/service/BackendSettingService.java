package com.systalk.sys.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.systalk.sys.model.User;
import com.systalk.sys.web.form.view.pageViewForm.backend.EditNewsAreaViewForm;
import com.systalk.sys.web.form.view.pageViewForm.backend.baseForm.SettingForm;
import com.systalk.sys.web.form.view.pageViewForm.backend.baseForm.UploadFileForm;
import com.systalk.sys.web.form.view.vo.news.DeleteTypeList;
import com.systalk.sys.web.form.view.vo.news.NewsTypeVo;

/**
 * 後臺維護 Service interface.
 * @author Richard
 * */
public interface BackendSettingService {

	/** 輪播圖片後臺管理 (圖片.標題.文字.狀態) 儲存. 
	 * @throws Exception */
	public void doBannerSettingSaveAction(UploadFileForm uploadFileForm, User sysUser) throws Exception;
	
	/** 輪播圖片後臺管理 刪除. 
	 * @throws Exception */
	public void doDeleteBannerAction(Integer seq) throws Exception;
	
	/** 最新消息 後臺管理儲存.
	 * @throws Exception */
	public void doNewsAreaSaveAction(EditNewsAreaViewForm viewForm, User sysUser) throws Exception;
	
	/** 最新消息分類 後臺管理儲存.
	 * @throws Exception */
	public String doNewsAreaTypeSaveAction(List<NewsTypeVo> newsAreaTypeList, List<DeleteTypeList> deleteTypeList, String locale) throws Exception;
	
	/** 新聞專區後臺管理 刪除. 
	 * @throws Exception */
	public void doDeleteNewsAreaAction(Integer seq) throws Exception;
	
	/** 行銷影片 後臺管理儲存.
	 * @throws Exception */
	public void doVideoSaveAction(SettingForm settingForm, User sysUser) throws Exception;
	
	/**行銷影片 後臺管理刪除.
	 * @throws Exception */
	public void doDeleteVideoAction(Integer seq) throws Exception;

	/** logo牆 後臺管理 (圖片.狀態.上傳日期) 儲存. 
	 * @throws Exception */
	public void doSaveLogoWallAction(UploadFileForm uploadFileForm, User sysUser) throws Exception;
	
	/** logo牆排序 後臺管理 儲存.
	 * @throws Exception */
	public void doSaveLogowallSortAction(List<Integer> seqSortList, User sysUser) throws Exception;
	
	/**logo牆 後臺管理刪除.
	 * @throws Exception */
	public void doDeleteLogoWallAction(Integer seq) throws Exception;
	
	/** 成功案例 後臺管理儲存.
	 * @throws Exception */
	public void doSuccessCaseSaveAction(UploadFileForm uploadFileForm, User sysUser) throws Exception;
	
	/**成功案例 後臺管理刪除.
	 * @throws Exception */
	public void doDeleteSuccessCaseAction(Integer seq) throws Exception;
	
	/** 編輯器圖片上傳.
	 * @throws Exception */
	public String doEditorUploadImageAction(MultipartFile file, String type, MultipartHttpServletRequest request) throws Exception;
}
