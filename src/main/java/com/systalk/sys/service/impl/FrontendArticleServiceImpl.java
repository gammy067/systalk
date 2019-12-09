package com.systalk.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.systalk.sys.dao.ArticleDao;
import com.systalk.sys.dao.CodeTableDao;
import com.systalk.sys.enums.ArticleSortType;
import com.systalk.sys.model.CodeTable;
import com.systalk.sys.service.FrontendArticleService;
import com.systalk.sys.service.impl.helper.FrontendArticleServiceHelper;
import com.systalk.sys.web.form.view.bean.ArticleBean;
import com.systalk.sys.web.form.view.pageViewForm.frontend.FrontendArticleForm;

/**
 * 思拓研究室serviceImpl.
 */
@Transactional(rollbackFor = Exception.class)
@Service("FrontendArticleService")
public class FrontendArticleServiceImpl implements FrontendArticleService {
	
	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ArticleDao articleDao;
	
	@Autowired
	private CodeTableDao codeTableDao;

	/**
	 *  取得思拓研究室顯示 viewForm. 
	 *
	 * @return the frontend index form
	 * @throws Exception the exception
	 */
	@Override
	public FrontendArticleForm initArticleForm() throws Exception {
		FrontendArticleServiceHelper helper = FrontendArticleServiceHelper.getInstance();

		// 取得思拓研究室資料
		FrontendArticleForm viewForm = helper.getFrontendArticleForm();
		return viewForm;
	}

	/**
	 * 文章搜尋.
	 *
	 * @param parm the parm
	 * @return the list
	 */
	@Override
	public List<ArticleBean> doQueryArticleAction(String queryTypeSeq, String querStr, String querySortSeq) {
		// 分類代碼
		int categorySeq = Integer.valueOf(queryTypeSeq);
		// 排序代碼
		int codeSeq = Integer.valueOf(querySortSeq);

		String code = ArticleSortType.PUBLISHING_DATE.getCode();

		CodeTable codeTable =  codeTableDao.findOne(codeSeq);
		if (codeTable != null) {
			code = codeTable.getCode();
		}
		// 模糊查詢(作者、標題、標籤)
		List<ArticleBean> beanList = articleDao.fuzzyQueryByJoinCodeTable(categorySeq, querStr, code);

		return beanList;
	}
}
