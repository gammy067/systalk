package com.systalk.sys.auth.listener;

import java.util.HashSet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionCounter  implements HttpSessionListener{

	public final static String KEY_PAGE_REDIRECT = "page.redirect";

	
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		ServletContext application = session.getServletContext();
		
		// 在application範圍由一個HashSet集保存所有的session
		
		HashSet sessions = (HashSet) application.getAttribute("sessions");
		
		if (sessions == null) {
			
		sessions = new HashSet();
		
		application.setAttribute("sessions", sessions);
		
		}

		// 新創建的session均添加到HashSet集中

		sessions.add(session);
		
		// 可以在別處從application範圍中取出sessions集合
		
		// 然後使用sessions.size()獲取當前活動的session數，即為「在線人數」
	}
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();

		ServletContext application = session.getServletContext();

		HashSet sessions = (HashSet) application.getAttribute("sessions");

		// 銷毀的session均從HashSet集中移除

		sessions.remove(session);

    }
}