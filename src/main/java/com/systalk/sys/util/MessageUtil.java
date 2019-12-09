package com.systalk.sys.util;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;

public class MessageUtil {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
    private MessageSource messageSource;

	public String getMessage(String keyName, Object [] args, Locale locale){

		if(locale == null){
			//logger.info("locale is null set default Locale TAIWAN");
			locale = Locale.TAIWAN;
		}
	    String msg = "";
	    try{
	    	msg = messageSource.getMessage(keyName, args, locale);
	    }catch(NoSuchMessageException e){
	    	logger.error("No message found under code '" + keyName + "'");
	    }
	    
	    if(msg == null || msg.trim().equals("")){
	    	logger.error(keyName + " does not exist in properties");
	    }
		return msg;
	}
	
	/**
	 * 取得語系文字(預設為 Locale.TAIWAN).
	 *
	 * @param keyName the key name
	 * @return the message
	 */
	public String getMessage(String keyName){
		return this.getMessage(keyName, null, null);
	}
	
	/**
	 * 取得語系文字(預設為 Locale.TAIWAN) 傳參數.
	 *
	 * @param keyName the key name
	 * @return the message
	 */
	public String getMessage(String keyName, Object [] args){
		return this.getMessage(keyName, args, null);
	}
	
	/**
	 * 取得當前設定的語系.
	 *
	 * @param keyName the key name
	 * @return the current locale message
	 */
	public String getCurrentLocaleMessage(String keyName){
		// Get current locale
		Locale locale = LocaleContextHolder.getLocale();
		return this.getMessage(keyName, null, locale);
	}
}
