package com.systalk.sys.util;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;

import com.sun.jersey.spi.inject.Errors.ErrorMessage;
import com.systalk.sys.auth.model.CustomUserDetails;
import com.systalk.sys.model.User;

/**
 * Created by yangyibo on 8/23/17.
 */
public class SessionUtil {

    /**
     * 辨别用户是否已经登录
     *
     * @param request
     * @param sessionRegistry
     * @param loginedUser
     * @throws Exception 
     */
    public static void deleteSameUser(HttpServletRequest request, SessionRegistry sessionRegistry, User loginedUser) throws Exception {
        SecurityContext sc = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        List<SessionInformation> sessionsInfo;
       
        if(sc != null) {
            sessionsInfo = sessionRegistry.getAllSessions(sc.getAuthentication().getPrincipal(), true);
            String currentSessionId;
            if (null != sessionsInfo && sessionsInfo.size() == 0) {
                sessionRegistry.registerNewSession(request.getSession().getId(), sc.getAuthentication().getPrincipal());
                sessionsInfo = sessionRegistry.getAllSessions(sc.getAuthentication().getPrincipal(), false);
            }

            currentSessionId = sessionsInfo.get(0).getSessionId();
            List<Object> o = sessionRegistry.getAllPrincipals();
            for (Object principal : o) {
                if (principal instanceof User && (loginedUser.getUserName().equals(((User) principal).getUserName()))) {
                    List<SessionInformation> oldSessionsInfo = sessionRegistry.getAllSessions(principal, false);
                    if (null != oldSessionsInfo && oldSessionsInfo.size() > 0 && !oldSessionsInfo.get(0).getSessionId().equals(currentSessionId)) {
                        for (SessionInformation sessionInformation : sessionsInfo) {
                            //当前session失效
                            sessionInformation.expireNow();
                            sc.setAuthentication(null);
                            sessionRegistry.removeSessionInformation(currentSessionId);
                            // throw new GeneralServerExistException(ErrorMessage.ALONG_LOGIN_ERROTR.toString());
                            throw new Exception("登入錯誤");
                        }
                    }
                }
            }
        }
    }

    /**
     * 剔除前一个用户
     *
     * @param request
     * @param sessionRegistry
     * @param loginedUser
     * @throws ServletException 
     * @throws Exception 
     */
    public static void dropPreviousUser(HttpServletRequest request, SessionRegistry sessionRegistry, CustomUserDetails loginedUser) throws ServletException  {
        SecurityContext sc = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        List<SessionInformation> sessionsInfo;
        
        if(sc != null) {
        	sessionsInfo = sessionRegistry.getAllSessions(sc.getAuthentication().getPrincipal(), false);
        	
            if (sessionsInfo.size() > 0) {
                String  currentSessionId = sessionsInfo.get(0).getSessionId();
                List<Object> o = sessionRegistry.getAllPrincipals();
                for (Object principal : o) {
                    if (principal instanceof CustomUserDetails && (loginedUser.getUsername().equals(((CustomUserDetails) principal).getUsername()))) {
                        List<SessionInformation> oldSessionsInfo = sessionRegistry.getAllSessions(principal, false);
                        if (null != oldSessionsInfo && oldSessionsInfo.size() > 0 && !oldSessionsInfo.get(0).getSessionId().equals(currentSessionId)) {
                            for (SessionInformation sessionInformation : oldSessionsInfo) {
                                //当前session失效
                                //send message
                                // sysMessageService.sendMessage(((User) principal).getUsername(), new SysMessage(null, Consts.NOTIFICATION_TYPE_HADLOGIN_CONTENT, 5, Consts.NOTIFICATION_ACCEPT_TYPE_HADLOGIN));
                                sessionInformation.expireNow();
                            }
                        }
                    }
                }
            }else {
                //throw new  GeneralServerExistException(ErrorMessage.ALONG_LOGIN_ERROTR.toString());
            	throw new ServletException("登入錯誤");
            }
        }
    }


    /**
     * session 失效
     *
     * @param request
     * @param sessionRegistry
     */
    public static void expireSession(HttpServletRequest request, User user, SessionRegistry sessionRegistry) {
        List<SessionInformation> sessionsInfo = null;
        if (null != user) {
            List<Object> o = sessionRegistry.getAllPrincipals();
            for (Object principal : o) {
                if (principal instanceof User && (user.getUserName().equals(((User) principal).getUserName()))) {
                    sessionsInfo = sessionRegistry.getAllSessions(principal, false);
                }
            }
        } else if (null != request) {
            SecurityContext sc = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
            if (null != sc.getAuthentication().getPrincipal()) {
                sessionsInfo = sessionRegistry.getAllSessions(sc.getAuthentication().getPrincipal(), false);
                sc.setAuthentication(null);
            }
        }
        if (null != sessionsInfo && sessionsInfo.size() > 0) {
            for (SessionInformation sessionInformation : sessionsInfo) {
                //当前session失效
                sessionInformation.expireNow();
                sessionRegistry.removeSessionInformation(sessionInformation.getSessionId());
            }
        }
    }
}