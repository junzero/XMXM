/*******************************************************************************
 * $Header: /opt/cvsroot/wiki/opensource/gocom/abframe2/src/org.gocom.abframe.auth/src/org/gocom/abframe/auth/taglib/PermissionTag.java,v 1.4 2009/04/14 03:54:32 caisy Exp $
 * $Revision: 1.4 $
 * $Date: 2009/04/14 03:54:32 $
 *
 *==============================================================================
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on 2008-8-17
 *******************************************************************************/


package com.gotop.taglib.auth;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log4j.Logger;

import com.gotop.util.Global;
import com.gotop.vo.system.MUOUserSession;

/**
 *
 * 权限标签<BR>
 * 当在页面的按钮或其他html元素设置了权限标签时，只有有功能权限的操作员才能看到，否则隐藏不显示.如：
 * <pre>
 *    &lt;abframe:permission>
 *      &lt;input type="button" value="提交" onClick="doSubmit();"/>
 *    &lt;/abframe:permission>
 * </pre>
 *
 * @author 林钟
 * wengzr (mailto:linzhong@gotop.net.cn)
 */
public class PermissionTag extends BodyTagSupport{
	
	protected static Logger log = Logger.getLogger(PermissionTag.class);

	private static final long serialVersionUID = 8121152106347375612L;
	private String roles;
	private String funs;
	
	public int doStartTag() {

		if((roles==null || roles.trim().equals("")) && (funs==null || funs.trim().equals(""))){
			return SKIP_BODY;
		}
		
		List hmp = RoleManager.getHmp();
		
		Set someSet = new HashSet();
		if(funs!=null){
			String[] funArra = funs.split(";");
			for (int i = 0; i < funArra.length; i++) {
				if(funArra[i]!=null && !funArra[i].trim().equals("")){
					for (int k = 0; k < hmp.size(); k++) {
						String[] role = (String[])hmp.get(k);
						if(role[0].equals(funArra[i])){
							someSet.add(role[1]);
						}
					}
				}
			}
		}
		if(roles!=null){
			String[] roleArra = roles.split(";");
			for (int i = 0; i < roleArra.length; i++) {
				if(roleArra[i]!=null && !roleArra[i].trim().equals("")){
					someSet.add(roleArra[i]);
				}
			}
		}
		
		HttpServletRequest request=(HttpServletRequest)pageContext.getRequest();
		MUOUserSession olUser = (MUOUserSession)request.getSession().getAttribute(Global.LOGON_USER_KEY);
		if(olUser==null){
			return SKIP_BODY;
		}
		String[] role = olUser.getRoleid();
		if(role==null){
			return SKIP_BODY;
		}
		for (Iterator iterator = someSet.iterator(); iterator.hasNext();) {
			String name = (String) iterator.next();
			for (int i = 0; i < role.length; i++) {
				if(name.equals(role[i])){
					return EVAL_BODY_INCLUDE;
				}
			}
		}
		return SKIP_BODY;
	}

	public int doAfterBody()throws JspException {
		return super.doAfterBody();
	}

	public int doEndTag() {
		return EVAL_PAGE;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getFuns() {
		return funs;
	}

	public void setFuns(String funs) {
		this.funs = funs;
	}
	
	public static void main(String[] arg){
		PermissionTag pt = new PermissionTag();
		pt.setFuns("HYGL-HYWH_JKINFO");
//		pt.setRoles("SYS-KHJL1;SYS-PTRY1");
		System.out.println(pt.doStartTag());
	}
}
