package com.gotop.taglib.auth;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.gotop.util.Global;
import com.gotop.vo.system.MUOUserSession;
import com.opensymphony.xwork2.ActionContext;
import com.primeton.utils.Condb;

public class RoleManager {
	private static List lhmp = null;
	public static void reloadAuth(){
		try {
			Condb condb = new Condb();
			String sql = "select * from ac_rolefunc";
			com.primeton.utils.ResultSet rs = condb.executeQuery(sql);
			List lhmpTemp = new ArrayList();
			while(rs.next()){
				String funccode = rs.getString("funccode");
				String rolealias = rs.getString("roleid");
				String[] role = {funccode,rolealias};
				lhmpTemp.add(role);
			}
			condb.close();
			lhmp = lhmpTemp;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static List getHmp() {
		if(lhmp == null){
			try {
				reloadAuth();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return lhmp;
	}
	public static void setHmp(List hmp) {
		RoleManager.lhmp = hmp;
	}
	
	public static boolean isOpRole(String roles,String funs){
		if((roles==null || roles.trim().equals("")) && (funs==null || funs.trim().equals(""))){
			return false;
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
		
		Map session = ActionContext.getContext().getSession();
		MUOUserSession olUser = (MUOUserSession)session.get(Global.LOGON_USER_KEY);
		if(olUser==null){
			return false;
		}
		String[] role = olUser.getRoleid();
		if(role==null){
			return false;
		}
		for (Iterator iterator = someSet.iterator(); iterator.hasNext();) {
			String name = (String) iterator.next();
			for (int i = 0; i < role.length; i++) {
				if(name.equals(role[i])){
					return true;
				}
			}
		}
		return false;
	}
}
