package com.systalk.sys.dao.custom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.systalk.sys.model.BannerSetting;

@Transactional
@Repository
public class BannerSettingDaoImpl<T> implements  BannerSettingDaoCustom<T> {
private Logger logger = LoggerFactory.getLogger(getClass());
	
	@PersistenceContext
	private EntityManager em;


	@Override
	public void saveWithoutNull(BannerSetting bannerSetting) {
		Session session = em.unwrap(Session.class);
		session.save(bannerSetting);
	}
}
