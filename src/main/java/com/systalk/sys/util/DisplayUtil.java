package com.systalk.sys.util;

import org.apache.commons.lang3.StringUtils;

import com.systalk.sys.enums.Status;

public final class DisplayUtil {
	
	/**
	  *  上架/指定時間上架 i18n文字顯示.
	 *
	 * @param request
	 * @return
	 */
	public static String getDisplayStatus(String status) {
		MessageUtil mu = SpringUtil.getBean(MessageUtil.class);
		String displayStatus = "";

		if(StringUtils.isEmpty(status)) {
			return mu.getMessage("status.invalid");
		}
		if (StringUtils.equals(status, Status.EFFECTIVE.getCode())) {
			// 上架
			displayStatus = mu.getMessage("status.effective");
		} else {
			// 指定時間上架
			displayStatus = mu.getMessage("status.assign");
		}
		return displayStatus;
	}

	/**
	  *  成功案例 上架/下架 i18n文字顯示.
	 *
	 * @param request
	 * @return
	 */
	public static String getDisplaySuccessCaseStatus(String status) {
		MessageUtil mu = SpringUtil.getBean(MessageUtil.class);
		String displayStatus = "";

		if(StringUtils.isEmpty(status)) {
			return mu.getMessage("status.invalid");
		}
		if (StringUtils.equals(status, Status.EFFECTIVE.getCode())) {
			// 上架
			displayStatus = mu.getMessage("status.effective");
		} else {
			// 下架
			displayStatus = mu.getMessage("status.invalid");
		}
		return displayStatus;
	}
}
