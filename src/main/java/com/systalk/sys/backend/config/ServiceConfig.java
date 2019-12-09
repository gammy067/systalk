package com.systalk.sys.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.systalk.sys.util.MessageUtil;

/**
 * applicationContext-service.xml
 * 
 * @author Richard
 *
 */
@Configuration
@ComponentScan(basePackages = {"com.systalk.sys.util", "com.systalk.sys.service"})
//@ComponentScan(basePackages = {"com.thinkpower.tpu.util", "com.thinkpower.tpu.service", 
//		"com.thinkpower.tpu.schedule.job", "com.thinkpower.tpu.job.service"})
public class ServiceConfig {

	//Load Services and Utility
	
	@Bean
	public MessageUtil messageUtil(){
		MessageUtil messageUtil = new MessageUtil();
		
		return messageUtil;
	}
	
}
