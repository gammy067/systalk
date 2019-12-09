package com.systalk.sys.controller.validator.common;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.validation.Errors;

import com.systalk.sys.util.CommonUtil;

/**
 * 上架/下架日期驗證.
 */
public class DateValidator {

    public void validate(String pushingDate, String invalidDate, Errors errors, String ... fieldNames) {
    	boolean validate = true;
    	
    	if(StringUtils.isEmpty(pushingDate)) {
    		errors.rejectValue(fieldNames[0], "", "請填入上架日期");
    		validate = false;
    	}
    	
    	if(StringUtils.isEmpty(invalidDate)) {
    		errors.rejectValue(fieldNames[1], "", "請填入下架日期");
    		validate = false;
    	}
    	
    	if(validate) {
			try {
				// 日期格式 yyyy/MM/dd. 
				Date pDate = DateUtils.parseDate(pushingDate, CommonUtil.dateFormatCommon);
				Date iDate = DateUtils.parseDate(invalidDate, CommonUtil.dateFormatCommon);
				
	        	if(iDate.equals(pDate) || iDate.before(pDate)) {
	        		errors.rejectValue(fieldNames[1], "", "下架日期須大於上架日期");
	        	}
	        	
			} catch (ParseException e) {
				errors.rejectValue(fieldNames[1], "", "日期格式有誤");
			}
    	}
    }
}
