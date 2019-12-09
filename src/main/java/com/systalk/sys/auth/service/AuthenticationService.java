package com.systalk.sys.auth.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import com.systalk.sys.auth.model.CustomUserDetails;
import com.systalk.sys.dao.UserDao;
import com.systalk.sys.enums.Role;
import com.systalk.sys.model.User;
import com.systalk.sys.util.SessionUtil;

@Service("AuthenticationService")
public class AuthenticationService implements AuthenticationProvider {

	@Autowired
	private UserDao userDao;
	

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userId = authentication.getName();
		String userPwd = authentication.getCredentials().toString();
		User loginedUser = userDao.findByUserIdAndUserPwd(userId, userPwd);
		
		if (loginedUser != null) {
				// 只有一位後台管理員 登錄成功 role = ROLE_ADMIN
				String[] roleArr = {Role.ROLE_ADMIN.getCode()};
				List<GrantedAuthority> grantedAuthority = AuthorityUtils.createAuthorityList(roleArr);

				CustomUserDetails userDetails = new CustomUserDetails(userId, userPwd, grantedAuthority);			
				return new UsernamePasswordAuthenticationToken(userDetails, userPwd, grantedAuthority);
		}
		return null;
	}

	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
