package com.systalk.sys.auth.filter;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.WebUtils;

import com.systalk.sys.model.User;

public class CustomerFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpSession session = request.getSession();
    	
		if (StringUtils.contains(request.getRequestURI(), "backend")) {
			User loginUser = (User) WebUtils.getSessionAttribute(request, "loginUser");
//			if (loginUser == null) {
//				// 登入失敗或是權限不同意
//				session.invalidate();
//				response.sendRedirect(request.getContextPath() + "/backend");
//				return;
//			}
		}


//        if(!request.authenticate(response)) {
//        	// 登入失敗或是權限不同意
//    		session.invalidate();
//    		response.sendRedirect(request.getContextPath() + "/");
//    		return;
//        }
//        
//        
//        if(loginUser == null ) {
//        	// 登入失敗或是權限不同意
//    		session.invalidate();
//    		response.sendRedirect(request.getContextPath() + "/");
//    		return;
//        }
        
        filterChain.doFilter(servletRequest, servletResponse);
    }
}