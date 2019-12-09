package com.systalk.sys.service;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.data.domain.Sort;

import com.systalk.sys.web.form.view.bean.BannerSettingBean;
import com.systalk.sys.web.form.view.bean.LogoWallBean;
import com.systalk.sys.web.form.view.bean.NewsAreaBean;
import com.systalk.sys.web.form.view.bean.NewsAreaTypeBean;
import com.systalk.sys.web.form.view.bean.SuccessCaseBean;
import com.systalk.sys.web.form.view.bean.VideoSettingBean;

/**
 * 後臺維護 Service interface.
 * @author Richard
 * */
public interface BackendService {

	/** 查詢首頁輪播圖片. 
	 * @throws Exception */
	public List<BannerSettingBean> queryBannerSetting() throws Exception;

	/** 查詢 輪播圖片編輯 BySeq. 
	 * @throws Exception */
	public BannerSettingBean queryBannerSettingBySeq(Integer seq) throws Exception;

	/** 查詢最新消息資料. 
	 * @throws Exception */
	public List<NewsAreaBean> queryNewsArea() throws Exception;
	
	/** 查詢最新消息分類資料. 
	 * @throws Exception */
	public Map<Locale, List<NewsAreaTypeBean>> queryNewsAreaType() throws Exception;
	
	/** 查詢 新聞專區編輯. 
	 * @throws Exception */
	public NewsAreaBean queryNewsAreaBySeq(Integer seq) throws Exception;
	
	/** 查詢行銷影片資料. 
	 * @throws Exception */
	public List<VideoSettingBean> queryVideoSetting() throws Exception;
	
	/** 查詢 行銷影片編輯 BySeq. 
	 * @throws Exception */
	public VideoSettingBean queryVideoSettingBySeq(Integer seq) throws Exception;
	
	/** 查詢品牌牆. 
	 * @throws Exception */
	public List<LogoWallBean> queryLogoWall() throws Exception;
	
	/** 查詢品牌牆 編輯 BySeq. 
	 * @throws Exception */
	public LogoWallBean queryLogoWallBySeq(Integer seq) throws Exception;

	/** 查詢品牌牆 查詢條件:依優先順序排序. 
	 * @throws Exception */
	public List<LogoWallBean> queryLogoWallBySort(Sort sort) throws Exception;
	
	/** 查詢成功案例 編輯 BySeq. 
	 * @throws Exception */
	public SuccessCaseBean querySuccessCaseBySeq(Integer seq) throws Exception;
	
	/** 查詢成功案例. 
	 * @throws Exception */
	public List<SuccessCaseBean> querySuccessCase() throws Exception;
}
