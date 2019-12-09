package com.systalk.backend.config;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * 
 * @author richard.hsu
 *
 */
@Configuration
@WebAppConfiguration
@Import({ DBTestConfig.class, JPAConfig.class, ServiceConfigTest.class })
public class ApplicationContextTest  {

	@Autowired
    private Environment environment;
	
	@Bean
	public ViewResolver viewResolver() {
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setViewClass(org.springframework.web.servlet.view.tiles3.TilesView.class);
		return viewResolver;
	}
	
	@Bean
	public MessageSource messageSource() {
	    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	    messageSource.setBasename("i18n/messages");
	    messageSource.setUseCodeAsDefaultMessage(true);
	    return messageSource;
	}
		
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	//多國語言設定 Start
	@Bean 
	public LocaleChangeInterceptor localeChangeInterceptor(){
	    LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
	    localeChangeInterceptor.setParamName("lang");//透過url path中 lang=en or lang=zh_tw 來變更語言
	    return localeChangeInterceptor;
	}

	@Bean(name="localeResolver")  //spring會去看localeResolver的預設值
	public CookieLocaleResolver sessionLocaleResolver(){
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setDefaultLocale(Locale.TAIWAN);
	    return localeResolver;
	}
	
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(localeChangeInterceptor());  //雖然是直接import進來，但還是要另外註冊它
//	    registry.addInterceptor(new CheckLoginInterceptor()).addPathPatterns("/**")
//		.excludePathPatterns("/login")
//		.excludePathPatterns("/denied");
	}
	//多國語言設定 End
	
	//for Apache Commons FileUpload
	@Bean(name = "filterMultipartResolver")
	public CommonsMultipartResolver multipartResolver(){
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("UTF-8");
//		String fileLimitSize = "20971520";
		String fileLimitSize = environment.getRequiredProperty("article.file.size");
		multipartResolver.setMaxUploadSize(Long.parseLong(fileLimitSize));
		return multipartResolver;
	}
	
    /*
     * FreeMarker configuration.
     */
    @Bean
    public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration() {
        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        bean.setTemplateLoaderPath("classpath:resources/template/");
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
}
