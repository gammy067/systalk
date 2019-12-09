package com.systalk.sys.service;

public interface ArticleService {

	public final static String PAGE_APPROVE_ARTICLE = "article/approveArticle";
	
	public final static String FIXED_ROOT_PATH = "File";
	
	
	public final static String FIXED_WORD_PATH = "word";
	public final static String FIXED_HTML_PATH = "html";
	public final static String FIXED_IMG_PATH = "images";
	
	public final static String FIXED_WORD_EXTENSION = ".docx";
	public final static String FIXED_HTML_EXTENSION = ".html";
	
	public final static String FIXED_HTML_COPY_FILENAME_APPEND = "_copy";
	public final static String FIXED_HTML_FRIENDLY_FILENAME_APPEND = "_f";
	public final static String FIXED_HTML_COPY_ZHCN_FILENAME_APPEND = "_copy_cn";
	public final static String FIXED_HTML_FRIENDLY_ZHCN_FILENAME_APPEND = "_f_cn";
	
	public final static String ARTICLE_RESOURCE_FILE = "file";
	public final static String ARTICLE_RESOURCE_FILE_USERNAME = "user.name";
	public final static String ARTICLE_RESOURCE_FILE_SYSNAME = "sys.name";
	public final static String ARTICLE_RESOURCE_FILE_PATH = "path";
	public final static String ARTICLE_RESOURCE_FILE_VIEW = "view";

	public final static String ARTICLE_RESOURCE_LINK = "link";
	public final static String ARTICLE_RESOURCE_LINK_URL = "url";
	public final static String ARTICLE_RESOURCE_LINK_DESC = "desc";
	
	public final static String ARTICLE_RESOURCE_TAG = "tag";
	public final static String ARTICLE_RESOURCE_TAG_NAME = "name";
	
	//status: 初始- 0, 待審核- 1, 審核不通過- 2, 上架- 3, 下架- 4, 待上架- 5
	/** 初始 */
	public final static String ARTICLE_STATUS_INIT = "0";
	/** 待審核 */
	public final static String ARTICLE_STATUS_APPROVE = "1";
	/** 審核不通過 */
	public final static String ARTICLE_STATUS_APPROVE_FAIL = "2";
	/** 上架 */
	public final static String ARTICLE_STATUS_RELEASE = "3";
	/** 下架 */
	public final static String ARTICLE_STATUS_SUSPEND = "4";
	/** 待上架 */
	public final static String ARTICLE_STATUS_WAIT_PUBLISH = "5";
	
	public final static String HTML_COPY_PREVENT_RIGHT_CLICK_TARGET = "<html>";
	public final static String HTML_COPY_PREVENT_RIGHT_CLICK_REPLACEMENT = "<html oncontextmenu='return false;'>";
	public final static String HTML_COPY_IMAGE_PATH_lOWERBOUND = "<img src=\"";
	public final static String HTML_COPY_IMAGE_PATH_UPPERBOUND = "\\\\tpu\\\\File\\\\html\\\\";
	public final static String HTML_COPY_LINK_LOWERBOUND = "<a";
	public final static String HTML_COPY_LINK_UPPERBOUND = "href";
	public final static String HTML_COPY_LINK_REPLACEMENT = " target='_blank' ";
	public final static String HTML_COPY_CATALOG_UPPERBOUND = " href=\"#";
	public final static String HTML_COPY_TITLE_ITEM_NUM_LOWERBOUND = "class=\"a2 a3 X1\"";
	public final static String HTML_COPY_TITLE_ITEM_NUM_UPPERBOUND = ">";
	public final static String HTML_COPY_TITLE_ITEM_REPLACEMENT = " style='font-size:16.0pt;' ";
	
	//友善閱讀加入style
	public final static String HTML_FRIENDLY_STYLE_LOWERBOUND = "<style>";
	public final static String HTML_FRIENDLY_STYLE_UPPERBOUND = "p{";
	public final static String HTML_FRIENDLY_STYLE_REPLACEMENT = "body{font-family:Verdana, Geneva, sans-serif, '微軟正黑體';}"
			+ ".header{border-bottom:1px dotted #888888;padding:20px 0;width: 595.0pt;}"
			+ ".box-btn{float:right;padding-top: 14px;}"
			+ ".btn{display: inline-block;font-size: 14px;text-align: center;white-space: "
					+ "nowrap;vertical-align: middle;border-radius: 4px 0 4px 0;transition:all ease-in .5s;margin-left:8px;}"
			+ ".btn a{display: block;color:#ffffff;text-decoration:none;padding: 6px 12px;}"
			+ ".btn-blue:hover, "
			+ ".btn-dblue:hover{opacity:.65;}"
			+ ".btn-blue{background-color: #036eb8;border: 1px solid #324a80;opacity:1;}"
			+ ".btn-dblue{background-color: #405fa3;border: 1px solid #324a80;opacity:1;}";
	//友善閱讀加入logo跟link to home and shareFB
	public final static String HTML_DOMAIN_NAME_URL = "systemDomainNameUrl";
	public final static String HTML_SHARE_TO_FB_URL = "shareThisPageToFBsUrl";
	public final static String HTML_FRIENDLY_HTML_LOGO_LOWERBOUND = "<body>";
	public final static String HTML_FRIENDLY_HTML_LOGO_UPPERBOUND = "<";
	public final static String HTML_FRIENDLY_HTML_LOGO_REPLACEMENT = "<div class='header'>"
			+ "<img style='height:46px;width:auto;margin-left: 35px;' src='"+ HTML_DOMAIN_NAME_URL +"tpu/images/logo.png' alt='ThinkPower University'>"
			+ "<div class='box-btn'>"
			+ "<span class='btn btn-blue'>"
			+ "<a href='"+ HTML_DOMAIN_NAME_URL +"tpu?f="+ ArticleService.FB_TOKEN +"'>回昕力大學瀏覽更多文章</a>"
			+ "</span>"
			+ "<span class='btn btn-dblue'>"
			+ "<a href='" + HTML_SHARE_TO_FB_URL +"?f="+ ArticleService.FB_TOKEN +"'>分享</a>"
			+ "</span>"
			+ "</div>"
			+ "</div>";
	public final static String HTML_FRIENDLY_HTML_LOGO_REPLACEMENT_NOSHARE = "<div class='header'>"
			+ "<img style='height:46px;width:auto;margin-left: 35px;' src='"+ HTML_DOMAIN_NAME_URL +"tpu/images/logo.png' alt='ThinkPower University'>"
			+ "<div class='box-btn'>"
			+ "<span class='btn btn-blue'>"
			+ "<a href='"+ HTML_DOMAIN_NAME_URL +"tpu?f="+ ArticleService.FB_TOKEN +"'>回昕力大學瀏覽更多文章</a>"
			+ "</span>"
			+ "</div>"
			+ "</div>";
	
	public final static String HTML_HEAD_METADATA_LOWERBOUND = "</style>";
	public final static String HTML_HEAD_METADATA_UPPERBOUND = "</head>";
	
	public final static String FB_TOKEN = "3dj6j8kd38895ksgtdddd93865jhr9sn3rqkh";
	
	public final static String ARTICLE_FLAG = "1";
	public final static String ARTICLE_FLAG_NULL = "0";
	
	public final static String PUBLISH_TYPE_IMMEDIATE = "immediate";
	public final static String PUBLISH_TYPE_SCHEDULE = "schedule";
	
//	public Map<Integer, Map<String, Object>> listTop(Integer topN);
//	public boolean setArticleFormFile(String fileUserName, String fileSysName, String fileAddr, ArticleForm article);
//	public String genFileSysName();
//	public boolean insertArticleAndArticleResource(ArticleForm articleForm);
//	public boolean insertArticleResource(int atc_seq, String rescType, String keyName, String value, String createDate);
//	public ArticleForm getArticleDetails(int atcSeq);
//	public Map<String, Object> listByCondition(Map<String, Map<String, String>> conditions, Integer currentPage, String from);
//	public Map<String, Object> listByConditionsForPopular(Map<String, Map<String, String>> conditions, Integer currentPage, String from);
//	public boolean suspendArticleAndArticleResource(int atc_seq, String status);
//	public List<UserForm> listAuthers(String[] checkedUserSeq);
//	public Map<String, Object> getApproveListByUserSeq(Integer userSeq, Integer currentPage, String status);
//	public Map<String, Object> getApproveList(Integer currentPage, String status);
//	public boolean updateArticleStatus(ArticleForm article, User loginUser, String contextPath) throws TpuRuntimeException;
//	public Map<String, Object> getAutherArticleList(Integer userSeq, Integer currentPage);
//	public Map<String, Object> getAutherArticleOnlineList(Integer userSeq, Integer currentPage);
//	public void insertArticleTopN(Article article);
//	
//	public Integer getArticleSeqByHtml(String htmlFileName);
//	/***
//	 * 取的 Html View URL
//	 * @param atcSeq
//	 * @return
//	 */
//	public String getHtmlView(int atcSeq);
//	
//	/***
//	 * 自動設定 ShareLink URL
//	 * @param atcForm
//	 */
//	public void autoSetShareLink(ArticleForm atcForm);
//	
//	/***
//	 * 自動設定 FbShareLink URL
//	 * @param atcForm
//	 * @param addtoken 是否需要 FB_TOKEN URL
//	 */
//	public void autoSetShareLink(ArticleForm atcForm, boolean addtoken);
//	
//	public Map<String, String> getArticleStatusTxtMap();
//	
//	/**
//	 * 
//	 * 將Facebook貼文的FeedId設為null
//	 * 
//	 * @param atc_seq
//	 * @return
//	 */
//	public boolean deleteFeed(int atc_seq);
//	
//	/**
//	 * 
//	 * 在Facebook貼文，並將Facebook的FeedId寫回資料庫
//	 * 
//	 * @param atc_seq
//	 * @return
//	 */
//	public boolean publishFeed(int atc_seq,String feedMessage,String shareLink);
//	
//	/**
//	 * 精選文章(手動設定)
//	 * 
//	 * @return
//	 */
//	public List<ArticleForm> getFeaturedArticle(List<Integer> mainCatCondition);
//	
//	/**
//	 * 
//	 * 最新文章(已審核通過時間最新為主 六篇，排除祕密花園與新手專區)
//	 * 
//	 * @return
//	 */
//	public List<ArticleForm> getNewestArticle(Integer newestArticleSize,List<Integer> mainCatCondition);
//	
//	/***
//	 * 
//	 * 熱門文章(近30天最多人點閱)
//	 * 
//	 * @param newestArticleSize
//	 * @return
//	 */
//	public List<ArticleForm> getPopularArticle(Integer popularArticleSize,List<Integer> mainCatCondition);
//	
//	/***
//	 * 
//	 * 相關文章
//	 * 
//	 * @param atcSeq
//	 * @return
//	 */
//	public List<ArticleForm> getRelatedArticle(int atcSeq,int count,List<Integer> mainCatCondition);
//	
//	/***
//	 * 
//	 * 同作者文章
//	 * 
//	 * @param atcSeq
//	 * @return
//	 */
//	public List<ArticleForm> getAuthorArticle(int authorSeq,int count,List<Integer> mainCatCondition);
//	
//	/**
//	 * 取得此文章最新通過審核atcSeq
//	 * 
//	 * @param atcSeq
//	 * @return
//	 */
//	public int getNewestArticleSeq(int atcSeq);
//	
//	/**
//	 * 
//	 * 取得全部精選文章的atcSeq
//	 * 
//	 * @return
//	 */
//	public List<Integer> getFeaturedArticleForManagement();
//	
//	/**
//	 * 
//	 * 更新精選文章的atcSeq
//	 * 
//	 * 
//	 */
//	public void updateFeaturedArticle(List<Integer> atcSeqList);
//	
//	/***
//	 * 
//	 * 各類最新文章
//	 * 
//	 * @param articleSize
//	 * @return
//	 */
//	public LinkedHashMap<Integer, List<ArticleForm>> getCategories(Integer articleSize,List<Integer> mainCatCondition,User loginUser);
//	
//	
//	/***
//	 * 
//	   * 各類文章的總數量
//	 * 
//	 * @param articleSize
//	 * @param mainCatCondition
//	 * @param loginUser
//	 * @return
//	 */
//	public LinkedHashMap<Integer, Integer> getArticleTotalNumberByMainCat();
//	
//	/***
//	 * 
//	   * 各類子文章的總數量
//	 * 
//	 * @param articleSize
//	 * @param mainCatCondition
//	 * @param loginUser
//	 * @return
//	 */
//	public LinkedHashMap<Integer, Integer> getArticleTotalNumberBySecCat();
	
}
