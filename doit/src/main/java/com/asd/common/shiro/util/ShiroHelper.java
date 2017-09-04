package com.asd.common.shiro.util;

import com.asd.common.constant.SysConst;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;

import java.util.Collection;

/**
 * Created by lenovo on 2017/1/6.
 * shiro的辅助类
 */
public class ShiroHelper {


    public static SessionDAO sessionDAO;

    /**
     * 获得当前用户名
     *
     * @return null:没有获取 String name
     */
    public static String getCurrentUsername() {
        Subject subject = getSubject();
        PrincipalCollection collection = subject.getPrincipals();
        if (null != collection && !collection.isEmpty()) {
            return (String) collection.iterator().next();
        }
        return null;
    }


    /**
     * 获取当前会话
     * @return session shiro的会话
     */
    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * 获取sessionId
     * @return id 获取sessionId
     */
    public static String getSessionId() {
        Session session = getSession();
        if (null == session) {
            return null;
        }
        return getSession().getId().toString();
    }

    /**
     * 根据登录的用户名获取当前已经认证的session会话
     * 方法有问题！！！！！
     * @param username 用户登录名称
     * @return activeSession
     */
    public static Session getSessionByUsername(String username){
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        for(Session session : sessions){
            if(null != session && StringUtils.equals(String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)), username)){
                //获取
                Boolean active = (Boolean)session.getAttribute(DefaultSubjectContext.AUTHENTICATED_SESSION_KEY);
                if(active!=null && active){
                    return session;
                }
            }
        }
        return null;
    }

    /**踢除用户
     * @param username 用户名
     */
    public static void kickOutUser(String username){
        Session session = getSessionByUsername(username);
        if(null != session && !StringUtils.equals(String.valueOf(session.getId()), ShiroHelper.getSessionId())){
            //设置标志位 通过拦截器来完成
            session.setAttribute(SysConst.FORCE_LOGOUT_FLAG, Boolean.TRUE);
        }
    }

    /**
     * 判断当前用户是否已通过认证
     * @return true or false
     */
    public static boolean hasAuthenticated() {
        return getSubject().isAuthenticated();
    }

    private static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

}
