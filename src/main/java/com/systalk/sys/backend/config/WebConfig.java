package com.systalk.sys.backend.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.systalk.sys.controller, com.systalk.sys.web.form"})
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
    private Environment environment;

	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions(new String[]{"/WEB-INF/tiles-defs.xml"});
		tilesConfigurer.setCheckRefresh(true);
		return tilesConfigurer;
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
        TilesViewResolver viewResolver = new TilesViewResolver();
        registry.viewResolver(viewResolver);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
//		拓首頁預設路徑 前臺 or 後臺
		//registry.addViewController("/").setViewName(environment.getProperty("systalk.default.url"));
		//registry.addViewController("/index").setViewName(environment.getProperty("systalk.default.url"));
	}


	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setDefaultLocale(Locale.TAIWAN);
	    return localeResolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		registry.addInterceptor(localeChangeInterceptor);
	}
	
	/*** 宣告static 位置**/
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/js/**").addResourceLocations("/js/");
		registry.addResourceHandler("/images/**").addResourceLocations("/images/");
		registry.addResourceHandler("/css/**").addResourceLocations("/css/");
		registry.addResourceHandler("/File/**").addResourceLocations("/File/");
		registry.addResourceHandler("/banner/**").addResourceLocations("/File/banner/images/");
		registry.addResourceHandler("/backendStatic/**").addResourceLocations("/backendStatic/");
		registry.addResourceHandler("sitemap.xml").addResourceLocations("sitemap.xml");
		registry.addResourceHandler("/edm/**").addResourceLocations("/edm/");
//		registry.addResourceHandler("/employee/**").addResourceLocations("/File/employee/");
		//registry.addResourceHandler("/File/employee/**").addResourceLocations("/File/employee/");
//		registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts/");
	}
}
