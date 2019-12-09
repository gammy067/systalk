package com.systalk.sys.auth.handler;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.web.util.WebUtils;

import com.systalk.sys.auth.model.CustomUserDetails;
import com.systalk.sys.auth.service.AuthenticationSuccessService;
import com.systalk.sys.dao.UserDao;
import com.systalk.sys.model.User;
import com.systalk.sys.util.SessionUtil;


public class AuthenticationSuccessHandler extends
	SavedRequestAwareAuthenticationSuccessHandler {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private AuthenticationSuccessService authenticationSuccessService;
	
	@Autowired
	private SessionRegistry sessionRegistry;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
		logger.info("Login success, get functions for user:" + user.getUsername());

		User loginedUser = userDao.findByUserIdAndUserPwd(user.getUsername(), user.getPassword());

		WebUtils.setSessionAttribute(request, "loginUser", loginedUser);
		super.onAuthenticationSuccess(request, response, authentication);
		
		// 不同session, 剔除前一个用户
		CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();
        if (currentUser != null) {
        	SessionUtil.dropPreviousUser(request, sessionRegistry, currentUser);
        }
		
		// 更新login 時間
		loginedUser.setLoginDate(new Date());
		authenticationSuccessService.updateUserLoginTime(loginedUser);
	}
}
