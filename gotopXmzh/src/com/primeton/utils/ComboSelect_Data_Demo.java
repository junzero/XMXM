/*******************************************************************************
 * $Header$
 * $Revision$
 * $Date$
 *
 *==============================================================================
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 * 
 * Created on 2011-10-5
 *******************************************************************************/


package com.primeton.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ComboSelect_Data_Demo {

	public List getData(HashMap hm) throws Exception{
		Condb con = new Condb();
		String sql = "select id, title, ontent, creater, agendadate, agendatype, remid, dateremid, tremidcontent, datastate, orgid from bagenda";
		System.out.println(sql);
		ResultSet rs = con.executeQuery(sql);
		List lhm = new ArrayList();
		rs.beforeFirst();
		while(rs.next()){
			BagendaImpl bagenda = new BagendaImpl();
			int id = rs.getInt("id");
			String title = rs.getString("title");
			bagenda.setId(id);
			bagenda.setTitle(title);
			lhm.add(bagenda);
		}
		rs.close();
		con.close();
		return lhm;
	}
	
	public String getDataStr(HashMap hm){
		String xfl = "";
		try {
			List result = this.getData(hm);
			xfl = XmlHelper.getXmlListBean(result);
			System.out.println(xfl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xfl;
	}
	
	public static void main(String[] args){
		
		ComboSelect_Data_Demo ddd = new ComboSelect_Data_Demo();
		try {
			String xfl = ddd.getDataStr(null);
			System.out.println(xfl);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
