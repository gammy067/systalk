package com.systalk.sys.backend.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.multipart.support.MultipartFilter;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {


	@Override
	protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
	   super.beforeSpringSecurityFilterChain(servletContext);

	   // CSRF for multipart form data filter:
	   FilterRegistration.Dynamic springMultipartFilter;
	   springMultipartFilter = servletContext.addFilter(
			   "springMultipartFilter", new MultipartFilter());
	   springMultipartFilter.addMappingForUrlPatterns(null, false, "/article/*");
	   springMultipartFilter.addMappingForUrlPatterns(null, false, "/online/*");
	   springMultipartFilter.addMappingForUrlPatterns(null, false, "/backend/*");
	   springMultipartFilter.addMappingForUrlPatterns(null, false, "/messageBoard/*");
	   springMultipartFilter.addMappingForUrlPatterns(null, false, "/user/*");
	   springMultipartFilter.addMappingForUrlPatterns(null, false, "/ad/*");
	}
}
