package com.asd.common.shiro.filter;

import com.asd.common.shiro.support.RedisManager;
import com.asd.common.shiro.support.RedisSessionDAO;
import com.asd.common.shiro.util.SerializeUtils;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.*;

public class KickoutFilter extends AccessControlFilter {
	// 静态注入
	public String kickoutUrl;
	// 在线用户
	public final static String ONLINE_USER = KickoutFilter.class.getCanonicalName() + "_online_user_";
	// 踢出状态，true标示踢出
	public final static String KICKOUT_STATUS = KickoutFilter.class.getCanonicalName() + "_kickout_status";
	// redis管理器
	public RedisManager redisManager;
	// session获取
	public RedisSessionDAO redisSessionDAO;
	//同一个账户最多允许的个数
	private int maxSession = 1;
	
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		HttpServletRequest httpRequest = ((HttpServletRequest) request);
		String url = httpRequest.getRequestURI();
		Subject subject = getSubject(request, response);

		// 如果没有登陆 直接返回true
		if(isLoginRequest(request,response)){
			return Boolean.TRUE;
		}
		// 如果是相关目录 or 如果没有登录 就直接return true
		if (url.startsWith("/open/") || (!subject.isAuthenticated() && !subject.isRemembered())) {
			return Boolean.TRUE;
		}

		// 获取session
		Session session = subject.getSession();
		Serializable sessionId = session.getId();
		// 获取userId
		String userId = (String) SecurityUtils.getSubject().getPrincipal();
		String key = ONLINE_USER + userId;


		// 从缓存获取用户-Session信息
		Deque<Serializable> sessions = redisManager.get(key, Deque.class);

		// 缓存中无数据则初始化队列，并保存到缓存
		if(sessions == null) {
			sessions = new LinkedList<Serializable>();
			saveSessions(key,sessions);
		}
		// 踢出标识
		Boolean marker = (Boolean) session.getAttribute(KICKOUT_STATUS);
		if(marker==null){
			marker = false;
		}

		//如果队列里没有此sessionId，且用户没有被踢出；放入队列
		if(!sessions.contains(sessionId) && !marker) {
			sessions.push(sessionId);
		}

		// 如果已经踢出：清除缓存中该sessionID信息
		// 1.如果是Ajax 访问，那么给予json返回值提示。
		// 2.如果是普通请求，直接跳转到登录页
		if (marker) {
			Map<String, String> resultMap = new HashMap<String, String>();
			// 判断是不是Ajax请求
			if (isAjax(request)) {
				resultMap.put("user_status", "300");
				resultMap.put("message", "您已经在其他地方登录，请重新登录！");
				out(response, resultMap);
			}
			sessions.remove(sessionId);
			saveSessions(key,sessions);
			return false;
		}


		//如果队列里的sessionId数超出最大会话数，开始踢人
		while(sessions.size() > maxSession) {
			Serializable kickoutSessionId = sessions.removeLast();
			try {
				Session kickoutSession = redisSessionDAO.getSession(kickoutSessionId);
				if(kickoutSession != null) {
					//设置会话的KICKOUT_STATUS属性表示踢出了
					kickoutSession.setAttribute(KICKOUT_STATUS, true);
					redisSessionDAO.update(kickoutSession);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		saveSessions(key,sessions);
		return true;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

		// 先退出
		Subject subject = getSubject(request, response);
		subject.logout();
		WebUtils.getSavedRequest(request);
		// 再重定向
		WebUtils.issueRedirect(request, response, kickoutUrl);
		return false;
	}

	private void saveSessions(String key,Deque sessions){
		redisManager.set(key.getBytes(), SerializeUtils.serialize(sessions), redisManager.getExpire());
	}

	private void out(ServletResponse hresponse, Map<String, String> resultMap) throws IOException {
		try {
			hresponse.setCharacterEncoding("UTF-8");
			PrintWriter out = hresponse.getWriter();
			out.println(JSONObject.fromObject(resultMap).toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			//LoggerUtils.error(getClass(), "KickoutSessionFilter.class 输出JSON异常，可以忽略。");
		}
	}

	public void setKickoutUrl(String kickoutUrl) {
		this.kickoutUrl = kickoutUrl;
	}

	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;
	}

	public void setRedisSessionDAO(RedisSessionDAO redisSessionDAO) {
		this.redisSessionDAO = redisSessionDAO;
	}

	/**
	 * 是否是Ajax请求
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isAjax(ServletRequest request) {
		return "XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"));
	}
	/**
	 * 是否包含sessionId
	 * @param sessionId
	 * @param sessions
	 * @return
	 */
	public boolean containsSessionId(Serializable sessionId,LinkedHashSet<Serializable> sessions){
		if(sessions==null || sessionId==null){
			return false;
		}
		for (Serializable serializable : sessions) {
			if(sessionId.equals(serializable)){
				return true;
			}
		}
		return false;
	}
}
