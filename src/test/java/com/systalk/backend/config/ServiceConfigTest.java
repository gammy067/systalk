package com.systalk.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.systalk.sys.util.MessageUtil;

/**
 * 
 * @author richard.hsu
 *
 */
@Configuration
@ComponentScan(basePackages = {"com.systalk.sys.util", "com.systalk.sys.service"})
public class ServiceConfigTest {

	//Load Services and Utility
	
	@Bean
	public MessageUtil messageUtil(){
		MessageUtil messageUtil = new MessageUtil();
		
		return messageUtil;
	}
	
}
