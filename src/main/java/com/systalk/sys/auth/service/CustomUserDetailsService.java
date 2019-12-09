package com.systalk.sys.auth.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.systalk.sys.auth.model.CustomUserDetails;
import com.systalk.sys.dao.UserDao;
import com.systalk.sys.enums.Role;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		logger.debug("login user:" + userId);
		com.systalk.sys.model.User user = userDao.findByUserId(userId);
		// 若找不到使用者或使用者密碼為空(不為從昕力大學註冊之使用者)
		if(user == null || user.getUserPwd() == null){
			logger.error("can not find User, by userId:" + userId);
			throw new UsernameNotFoundException("can not find User, by userId:" + userId);
		}
		
//		Role role = tkuUserService.getRoleByUserSeq(user.getUserSeq());
		
		// 2019.06.24 原tpu作法 預計在user欄位加個roleId
//		Role role = user.getRole();
//		List<GrantedAuthority> authorities = buildUserAuthority(role.getRoleId());
		// 先改成如下
		List<GrantedAuthority> authorities = buildUserAuthority(Role.ROLE_ADMIN.getCode());
		
		
		return buildUserForAuthentication(user, authorities);
	}
	
	private List<GrantedAuthority> buildUserAuthority(String userRole){
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		setAuths.add(new SimpleGrantedAuthority(userRole));
		 
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
		 
		return Result;
	}
	
	private CustomUserDetails buildUserForAuthentication(com.systalk.sys.model.User user, 
			List<GrantedAuthority> authorities) {
			return new CustomUserDetails(user.getUserId(), 
				user.getUserPwd(), user.getSuspend().equalsIgnoreCase("N"), true, true, true, authorities);
	}
}
