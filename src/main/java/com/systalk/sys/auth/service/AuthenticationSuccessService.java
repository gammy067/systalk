package com.systalk.sys.auth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.systalk.sys.dao.UserDao;
import com.systalk.sys.model.User;

@Service("authenticationSuccessService")
@Transactional (rollbackFor = Exception.class)
public class AuthenticationSuccessService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	UserDao userDao;
	
	/**
	 * 登入成功更新登入時間
	 */
	public void updateUserLoginTime(User user){
		logger.debug("updateLoginTime() Start, update login time...");
		
		User saveUser = userDao.save(user);
		boolean isSuccess = saveUser != null ? true :false;

		logger.debug("updateLoginTime() End, result: " + isSuccess); 
	}
}
