package com.gotop.util.session;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class OnlineUserBindingListener implements HttpSessionBindingListener,java.io.Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 6421393818174080145L;
	//	HttpServletRequest request;
//	public OnlineUserBindingListener(HttpServletRequest request) {
//		this.request = request;
//	}
//	
	public void valueBound(HttpSessionBindingEvent event) {
		HttpSession session = event.getSession();
		// 把用户名放入在线列表
		OnlineUserManager.addUserObje(session);
	}
	public void valueUnbound(HttpSessionBindingEvent event) {
		HttpSession session = event.getSession();
		OnlineUserManager.removeUserObje(session);
	}
}