package com.systalk.sys.backend.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.systalk.sys.auth.filter.CustomerFilter;
import com.systalk.sys.auth.handler.AuthenticationSuccessHandler;
import com.systalk.sys.auth.service.AuthenticationService;
import com.systalk.sys.auth.service.CustomUserDetailsService;


@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@ComponentScan(basePackages = {"com.systalk.sys.auth"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
    private Environment env;
	
    @Autowired
    SessionRegistry sessionRegistry;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		/**
		 * use user and password in database
		 */
		auth.userDetailsService(customUserDetailsService);
//			.passwordEncoder(new Md5PasswordEncoder());
		auth.eraseCredentials(false); ///登入後不要清除密碼 
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationService);
	}
	
	//for Apache Commons FileUpload
	@Bean(name = "filterMultipartResolver")
	public CommonsMultipartResolver multipartResolver(){
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("UTF-8");
		String fileLimitSize = env.getRequiredProperty("article.file.size");
//		String fileLimitSize = "20971520";
		multipartResolver.setMaxUploadSize(Long.parseLong(fileLimitSize));
		return multipartResolver;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//Set CharacterEncodingFilter before spring security filter
	    CharacterEncodingFilter filter = new CharacterEncodingFilter();
	    filter.setEncoding("UTF-8");
	    filter.setForceEncoding(true);
	    http.addFilterBefore(filter, CsrfFilter.class);

//		http.authorizeRequests().anyRequest().hasAnyRole("ROLE_ADMIN")
//		.and().authorizeRequests().antMatchers("/login**", "/content**", "/online**", "/js**").permitAll()
//		.and().formLogin().loginPage("/login").loginProcessingUrl("/loginAction").defaultSuccessUrl("/index",true)
//		.and().logout().logoutSuccessUrl("/login").permitAll();
	    // http.addFilterBefore(getCustomerFilter(), AbstractPreAuthenticatedProcessingFilter.class)
	    http.authorizeRequests()
	    .antMatchers("/js/**").permitAll()
		.antMatchers("/images/**").permitAll()
		.antMatchers("/css/**").permitAll()
		.antMatchers("/fonts/**").permitAll()
		
		.antMatchers("/").permitAll()
		.antMatchers("/login").permitAll()
		.antMatchers("/index").permitAll()
//		.antMatchers("/frontend**").permitAll()
		.antMatchers("/error").permitAll()
		.antMatchers("/backendStatic/**").permitAll()
		
		// 登入後可訪問
		.antMatchers("/backend/**").authenticated()
//		.antMatchers("/backend/**").hasAnyRole("ADMIN")
		
//		
//		.antMatchers("/login**", "/content**", "/online**", "/frontend**", "/backend**").permitAll()
//	    http.authorizeRequests().anyRequest().hasAnyRole("ROLE_ADMIN")
//	    .and().authorizeRequests().antMatchers("/login**", "/content**", "/online**", "/frontend**", "/backend**").permitAll()
	    
		.and().formLogin().loginPage("/login").passwordParameter("password").usernameParameter("username")
		.loginProcessingUrl("/loginAction").permitAll()
		.failureHandler(authenticationFailureHandler())
		.defaultSuccessUrl("/backend/index", true)
		.successHandler(authSuccessHandler())
		
		.and()
		.logout()
        .invalidateHttpSession(true)
        .logoutUrl("/logout")
        .logoutSuccessUrl("/login?logout=logout")
        .deleteCookies("JSESSIONID")
        .and().csrf().disable();
        
        http.sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry).expiredUrl("/login?logout=expire");
		
//		http.authorizeRequests().antMatchers("").hasAnyRole("ROLE_ADMIN","");
	}
	
	@Bean
	public CustomerFilter getCustomerFilter(){
		CustomerFilter customerFilter = new CustomerFilter();

		return customerFilter;
	}

	@Bean
	public SessionRegistry getSessionRegistry() {
		SessionRegistry sessionRegistry = new SessionRegistryImpl();
		return sessionRegistry;
	}

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(){
        AuthenticationFailureHandler authFailureHandler = new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

                //set our response to OK status
                httpServletResponse.setStatus(HttpServletResponse.SC_OK);

                String username = httpServletRequest.getParameter("username");
                logger.error("AuthenticationFailureHandler - username = "+username);
                
                String password = httpServletRequest.getParameter("password");
                logger.error("AuthenticationFailureHandler - password = "+password);

                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() +"/login?error=loginError");
            }
        };

        return authFailureHandler;
    }
	
	@Bean
	public AuthenticationSuccessHandler authSuccessHandler(){
		AuthenticationSuccessHandler authSuccessHandler = new AuthenticationSuccessHandler();
		/*
		 * 設定true下,登入會導向DefaultTargetUrl,false時,會導向登入前所點的功能,
		 * 但google登入不是經過此handler,無法導向登入前所點的功能
		 */
		authSuccessHandler.setAlwaysUseDefaultTargetUrl(true);
		
		authSuccessHandler.setDefaultTargetUrl("/backend/index");
		authSuccessHandler.setUseReferer(true);
		return authSuccessHandler;
	}
}
