package com.gotop.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.primeton.utils.Condb;

public class ActionRoleManager {
	protected static Map<String, ActionRoleBean> funlist;
	protected static Map<String, ActionRoleBean> modulelist;
	protected static Map<String, ActionRoleBean> actionlist;
	
	public static void loadAllActionRole() {
		Map<String, ActionRoleBean> retlist = null;
		Condb condb = new Condb();
		try {
			retlist = new HashMap<String,ActionRoleBean>();
			Connection conn = condb.getConn();
			PreparedStatement ppsm=null;
			ResultSet rs=null;
			String sql = "select namespace, action, rolealias, type, orglevel from abf_t_actionrole where type =3";
			ppsm=conn.prepareStatement(sql);
			rs=ppsm.executeQuery();
			putRetlist(retlist,rs,3);
			rs.close();
			ppsm.close();
			ActionRoleManager.funlist = retlist;
			retlist = new HashMap<String,ActionRoleBean>();
			sql = "select namespace, action, rolealias, type, orglevel from abf_t_actionrole where type =2";
			ppsm=conn.prepareStatement(sql);
			rs=ppsm.executeQuery();
			putRetlist(retlist,rs,2);
			rs.close();
			ppsm.close();
			ActionRoleManager.modulelist = retlist;
			
			retlist = new HashMap<String,ActionRoleBean>();
			sql = "select namespace, action, rolealias, type, orglevel from abf_t_actionrole where type =1";
			ppsm=conn.prepareStatement(sql);
			rs=ppsm.executeQuery();
			putRetlist(retlist,rs,1);
			rs.close();
			ppsm.close();
			ActionRoleManager.actionlist = retlist;
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			condb.close();
		}
	}

	public static void putRetlist(Map<String, ActionRoleBean> retlist,ResultSet rs,int optype) throws SQLException{
		while(rs.next()){
			String namespace = rs.getString("namespace");
			String action = rs.getString("action");
			String rolealias = rs.getString("rolealias");
			String tempkey;
			if(optype==2){
				tempkey = namespace;
			}else{
				tempkey = action;
			}
			ActionRoleBean arb = retlist.get(tempkey);
			if(arb==null){//如果没有取过值，刚直接创建一个新对象
				arb = new ActionRoleBean();
				arb.setAction(namespace);
				arb.setAction(action);
				arb.setType(optype);
				retlist.put(tempkey, arb);
			}
			if(rolealias!=null){//如果角色为空，则创建一个新的
				Set<String> rolealiasList = arb.getRolealias();
				if(rolealiasList==null){
					rolealiasList = new HashSet<String>();
					arb.setRolealias(rolealiasList);
				}
				rolealiasList.add(rolealias);
			}
			int orglevel = rs.getInt("orglevel");
			if(orglevel>0){
				arb.setOrglevel(orglevel);
			}
		}
	}
	
	public static Map<String, ActionRoleBean> getActionlist() {
		return actionlist;
	}

	public static void setActionlist(Map<String, ActionRoleBean> actionlist) {
		ActionRoleManager.actionlist = actionlist;
	}

	public static Map<String, ActionRoleBean> getModulelist() {
		return modulelist;
	}

	public static void setModulelist(Map<String, ActionRoleBean> modulelist) {
		ActionRoleManager.modulelist = modulelist;
	}

	public static Map<String, ActionRoleBean> getFunlist() {
//		if(actionlist==null){
		loadAllActionRole();
//	}
		return funlist;
	}

	public static void setFunlist(Map<String, ActionRoleBean> funlist) {
		ActionRoleManager.funlist = funlist;
	}
}
class ActionRoleBean {
	private String namespace;
	private String action;
	private Set<String> rolealias;
	private Integer type; 
	private Integer orglevel;
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Set<String> getRolealias() {
		return rolealias;
	}
	public void setRolealias(Set<String> rolealias) {
		this.rolealias = rolealias;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getOrglevel() {
		return orglevel;
	}
	public void setOrglevel(Integer orglevel) {
		this.orglevel = orglevel;
	}
	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
}