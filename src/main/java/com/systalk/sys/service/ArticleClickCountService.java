package com.systalk.sys.service;

import java.util.Date;

public interface ArticleClickCountService {
	
	public void countAtcClickMap(int atcSeq,Date clickTime);
	
	public void saveArticleClickCount();
}
