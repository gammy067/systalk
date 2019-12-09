package com.systalk.sys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.systalk.sys.model.User;

@Repository("UserDao")
public interface UserDao extends JpaRepository<User, Integer> {

	User findByUserId(String userId);

	User findByUserIdAndUserPwd(String userId, String userPwd);
}
