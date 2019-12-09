package com.systalk.sys.controller;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.systalk.sys.service.FrontendArticleService;
import com.systalk.sys.web.form.view.pageViewForm.frontend.FrontendArticleForm;
import com.systalk.sys.web.form.view.pageViewForm.frontend.article.ArticleQueryCondition;

/**
 * 思拓研究室Contoller.
 * @author Richard
 * */
@Controller
public class FrontendArticleContoller {

	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/** The frontend index service. */
	@Autowired
	FrontendArticleService frontendArticleService;

	/**
	 * 思拓研究室 article.
	 *
	 * @param model the model
	 * @param principal the principal
	 * @param request the request
	 * @return the string
	 * @throws Exception the exception
	 */
    @RequestMapping(value = {"/article"},  method = {RequestMethod.POST, RequestMethod.GET })
	public ModelAndView initArticle() throws Exception {
		logger.info("initArticle()");
		ModelAndView modelAndView = new ModelAndView("/article");

		FrontendArticleForm articleViewForm = frontendArticleService.initArticleForm();
		modelAndView.addObject("articleViewForm", articleViewForm);
		modelAndView.addObject("articleQueryCondition", new ArticleQueryCondition());

		return modelAndView;
	}

	/**
	 * 思拓研究室 文章搜尋.
	 * 分類 + 關鍵字 + 排序
	 * 
	 * @param model the model
	 * @param principal the principal
	 * @param request the request
	 * @return the string
	 * @throws Exception the exception
	 */
	@RequestMapping(value = { "/doArticleQueryAction" }, method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody String doArticleQueryAction(@RequestParam("queryType") String queryType,
			@RequestParam("queryStr") String queryStr,
			@RequestParam("querySort") String querySort) throws Exception {

		logger.info("doArticleQueryAction()");

		//List<ArticleBean> articleList = frontendArticleService.doQueryArticleAction(queryTypeSeq, queryStr, querySortSeq);
		
		//model.addAttribute("articleList", articleList);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", "success");
		String allJson = mapper.writeValueAsString(map);
		return allJson;
	}
}
