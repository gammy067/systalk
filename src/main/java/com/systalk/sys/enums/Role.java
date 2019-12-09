package com.systalk.sys.enums;

import org.apache.commons.lang3.StringUtils;

/**
 *  角色enum.
 * */
public enum Role {

	/** 角色: 管理員 ROLE_ADMIN.*/
	ROLE_ADMIN("ROLE_ADMIN"),
	
	UNKNOW("UNKNOW");

	private String role;

	private Role(String role) {
		this.role = role;
	}

	public static Role getRoleType(String s) {
		for (Role role : values()) {
			if (StringUtils.equals(role.getCode(), s)) {
				return role;
			}
		}
		return UNKNOW;
	}

	public String getCode() {
		return role;
	}
}
