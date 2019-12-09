package com.systalk.sys.service.impl;


import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.systalk.sys.dao.ArticleClickCountDao;
import com.systalk.sys.model.ArticleClickCount;
import com.systalk.sys.service.ArticleClickCountService;

@Service("articleClickCountService")
public class ArticleClickCountServiceImpl implements ArticleClickCountService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	ArticleClickCountDao articleClickCountDao;

	// 建立紀錄點擊數的Map
	public static Map<Integer, ArticleClickCount> atcClickMap  = new ConcurrentHashMap <Integer, ArticleClickCount>();
	
	//server開啟前會自動建立個文章的點擊數Map
//	@PostConstruct
//	private void initArticleClickCount(){
//		
//		logger.debug("initArticleClickCount() Start");	
//		List<ArticleClickCount> atcList = articleClickCountDao.findAll();
//		if(CollectionUtils.isNotEmpty(atcList)){
//			for (ArticleClickCount acc : atcList) {
//				atcClickMap.put(acc.getAtcSeq(), acc);
//			}
//		}
//		else {
//			logger.debug("initArticleClickCount() is empty");
//		}
//			logger.debug("initArticleClickCount() End ");	
//	}

	/**
	 * 每次點擊文章計算被點擊次數.
	 *
	 * @param atcSeq the atc seq
	 * @param clickTime the click time
	 */
	@Override
	public synchronized void countAtcClickMap(int atcSeq, Date clickTime){
		logger.debug("countAccSeqMap() Start");

		if (atcClickMap != null){

			ArticleClickCount acc = atcClickMap.get(atcSeq);
			// 未被點擊過的文章
			if(acc == null){
				ArticleClickCount newAcc = new ArticleClickCount();
				newAcc.setAtcSeq(atcSeq);
				newAcc.setClickCount(1);
				newAcc.setCreateDate(clickTime);
				newAcc.setLastDate(clickTime);
				atcClickMap.put(atcSeq, newAcc);
				
				logger.debug("new atc :" + atcSeq + " ,clickCount : " + newAcc);
				
			// 已被點擊過但未被更新至資料庫的文章, 再次被點擊
			} else {
				acc.setClickCount(acc.getClickCount() + 1);
				// acc.setCreateDate(clickTime);
				acc.setLastDate(clickTime);

				atcClickMap.put(atcSeq, acc);
				logger.debug("countAtcSeqMap() End atcSeq :" + atcSeq + " ,clickCount : " + acc);
			}
		}
		else {
			logger.error("countAtcSeqMap is null");
		}
	}
	
	/**
	 * 定時更新文章點擊數.
	 */
	@Scheduled(fixedDelay = 300000)
	@Transactional (rollbackFor = Exception.class)
	@Override
	public synchronized void saveArticleClickCount(){
		logger.debug("saveArticleClickCount() Start ...");
		//建立新Map用於insert or update
		//建立紀錄點擊數 DB Map
		Map<Integer, ArticleClickCount> atcClickMapFromDB  = new ConcurrentHashMap <Integer, ArticleClickCount>();
		List<ArticleClickCount> atcList = articleClickCountDao.findAll();

		if(CollectionUtils.isNotEmpty(atcList)){
			for (ArticleClickCount acc : atcList) {
				atcClickMapFromDB.put(acc.getAtcSeq(), acc);
			}
		}

		if (MapUtils.isNotEmpty(atcClickMap)){
			atcClickMap.forEach((seq, acc) -> {
				ArticleClickCount accFromDB = atcClickMapFromDB.get(seq);
				if (accFromDB == null) {
					atcClickMap.put(seq, acc);
					
				} else {
					accFromDB.setClickCount(accFromDB.getClickCount() + atcClickMap.get(seq).getClickCount());
					// accFromDB.setCreateDate(atcClickMap.get(seq).getCreateDate());
					accFromDB.setLastDate(atcClickMap.get(seq).getLastDate());
					
					atcClickMap.put(seq, accFromDB);
				}
			});
			
			articleClickCountDao.save(atcClickMap.values());
		}

		// 初始map 重新計算
		atcClickMap = new ConcurrentHashMap <Integer, ArticleClickCount>();

		logger.debug("saveArticleClickCount() End, size = " + atcClickMap.size());	
	}
}
