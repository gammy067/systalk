package com.systalk.sys.dao.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.systalk.sys.enums.ArticleSortType;
import com.systalk.sys.web.form.view.bean.ArticleBean;

@Transactional
@Repository
public class ArticleDaoImpl<T> implements  ArticleDaoCustom<T> {
private Logger logger = LoggerFactory.getLogger(getClass());
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<ArticleBean> findAllToViewBean() {
		logger.debug("ArticleDaoImpl findAllToViewBean()");

		String queryStr =
			    "SELECT NEW com.systalk.sys.web.form.bean.forntend.article.ArticleBean(a) " +
			    "from Article a";

			TypedQuery<ArticleBean> query = em.createQuery(queryStr, ArticleBean.class);
			List<ArticleBean> results = query.getResultList();

		return results;
	}

	/**
	 * 模糊查詢(作者、標題、標籤).
	 *
	 * @param queryParam the query param
	 * @return the list
	 */
	@Override
	public List<ArticleBean> fuzzyQueryByJoinCodeTable(int categorySeq, String queryStr, String code) {
		logger.debug("ArticleDaoImpl fuzzyQueryByJoinCodeTable()");
		StringBuffer sb = new StringBuffer();
		
		// 排序的欄位名稱
		String sortField = getSortField(code);

		sb.append("select new com.systalk.sys.web.form.bean.forntend.article.ArticleBean(a) ");
		sb.append("from Article a, CodeTable c where a.categorySeq = c.codeSeq ");
		sb.append("and a.status=1 ");
		sb.append("and a.categorySeq = :categorySeq ");
		sb.append("and (a.atcTopic like :queryStr or a.author like :queryStr or c.codeName like :queryStr) ");
		sb.append("order by a." + sortField);
		
		TypedQuery<ArticleBean> query = em.createQuery(sb.toString(), ArticleBean.class);
		query.setParameter("categorySeq", categorySeq);
		query.setParameter("queryStr", "%" + queryStr +"%");

		List<ArticleBean> results = query.getResultList();
		
		return results;
	}
	
	/**
	 * 取得排序的欄位.
	 *
	 * @param code the code
	 * @return the sort field
	 */
	private String getSortField(String code) {
		ArticleSortType sortType = ArticleSortType.getSortType(code);
		if(ArticleSortType.UNKNOW.equals(sortType)) {
			return ArticleSortType.PUBLISHING_DATE.getCode();
		} else {
			return sortType.getCode();
		}
	}
}
