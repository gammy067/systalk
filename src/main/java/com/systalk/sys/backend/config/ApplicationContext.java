package com.systalk.sys.backend.config;

import java.io.IOException;
import java.util.Properties;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * dispatcher-servlet.xml
 * 
 * @author Richard
 *
 */
// 暫不啟用排程
// @EnableScheduling
@Configuration
@EnableWebMvc
@EnableAsync
@Import({ SecurityConfig.class, DBConfig.class, JPAConfig.class , ServiceConfig.class })
public class ApplicationContext implements WebMvcConfigurer  {
	
	@Autowired
    private Environment environment;
	
    /*
     * FreeMarker configuration.
     */
    @Bean
    public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration() {
        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        bean.setTemplateLoaderPath("classpath:/template/");
        return bean;
    }

	//velocity
	@Bean(name="velocityEngine")
	public VelocityEngine velocityEngine() throws VelocityException, IOException{
		VelocityEngineFactoryBean factory = new VelocityEngineFactoryBean();
		Properties props = new Properties();
		props.put("resource.loader", "class");
		props.put("class.resource.loader.class", 
				  "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		factory.setVelocityProperties(props);
		
		return factory.createVelocityEngine();
	}
	
	//spring mail sender
	@Bean(name="mailsender")
	public JavaMailSender mailSender(){
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setDefaultEncoding("UTF-8");
		String host = environment.getRequiredProperty("st.mail.host");
		Integer port = Integer.parseInt(environment.getRequiredProperty("st.mail.port"));
		
		mailSender.setHost(host);
		mailSender.setPort(port);
		mailSender.setUsername(environment.getRequiredProperty("st.mail.username"));
		mailSender.setPassword(environment.getRequiredProperty("st.mail.password"));
		
		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.debug", true);
		javaMailProperties.put("mail.smtp.sendpartial", true);
		javaMailProperties.put("mail.smtp.host", host);
		javaMailProperties.put("mail.smtp.auth", true);
		javaMailProperties.put("mail.smtp.starttls.enable", true);
		javaMailProperties.put("mail.smtp.port", port);
		
		mailSender.setJavaMailProperties(javaMailProperties);
		return mailSender;
	}
	
	@Bean("messageSource")
	public MessageSource messageSource(){
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource(); 
		messageSource.setBasename("classpath:i18n/messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setUseCodeAsDefaultMessage(true);
		 messageSource.setCacheSeconds(5);
		return messageSource;
	}
	
	@Bean
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}

	@Override
	public Validator getValidator() {
		return validator();
	}
}
