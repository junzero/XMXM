package com.gotop.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.gotop.util.security.ForUtil;

public class SourcesHelper {
	
	@SuppressWarnings("unused")
	private Vector xmlVector = null;
	private FileInputStream file = null;
	
	@SuppressWarnings("unused")
	private Connection con = null;
	@SuppressWarnings("unused")
	private String url = "";
	private boolean ishave = false;
	private int index = 0;
	private List sources = null;
	private String Driver = "";
	private String user = "";
	private String pwd = "";
	
	private PreparedStatement pstm = null;
	private ResultSet rs = null;

	@SuppressWarnings("deprecation")
	public SourcesHelper(HttpServletRequest request,String name) throws IOException, SQLException {
		try {
			StringBuffer filePath = new StringBuffer(request.getRealPath("/"));
			filePath.append("/conf/sources.xml");
			file = ForUtil.createFileInputStream(filePath.toString());
			xmlVector = new Vector();
			SAXBuilder sax = new SAXBuilder();
			Document doc = sax.build(file);
			Element root = doc.getRootElement();
			sources = root.getChildren();
			Element source = null;
			
			for(int i=0;i<sources.size();i++){
				source = (Element) sources.get(i);
				if(source.getChild("name").getText().equals(name)){
					ishave = true;
					index = i;
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			file.close();
			file = null;
		}
	}
	
	public boolean has(){
		return ishave;
	}
	
	public void generURL() throws Exception{
		if(has()){
			Element source = (Element) sources.get(index);
			String type = source.getChild("type").getText();
			String ip = source.getChild("ip").getText();
			String port = source.getChild("port").getText();
			String database = source.getChild("database").getText();
			user = source.getChild("user").getText();
			pwd = source.getChild("pwd").getText();
			if(type.equalsIgnoreCase("oracle")){
				url = "jdbc:oracle:thin:@"+ip+":"+port+":"+database;
				Driver = "oracle.jdbc.OracleDriver";
			}else if(type.equalsIgnoreCase("sqlserver")){
				url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database;
				Driver = "net.sourceforge.jtds.jdbc.Driver";
			}else{
				throw new Exception("不支持当前给出的数据库连接描述!!");
			}
		}
	}

	public Object query(Class clas) throws SQLException {
		try {
			this.generURL();
			Class.forName(Driver);
			con = DriverManager.getConnection(url, user, pwd);
			String tableName =clas.getName().substring(clas.getName().lastIndexOf(".")+1);
			String sql = "SELECT * FROM " + tableName;
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			return r2b(rs,clas);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			rs.close();
			pstm.close();
			con.close();
			rs = null;
			pstm = null;
			con = null;
		}
		return null;
	}
	
	public Object query(String sql,Object[] params) throws SQLException {
		try {
			this.generURL();
			Class.forName(Driver);
			con = DriverManager.getConnection(url, user, pwd);
			pstm = con.prepareStatement(sql);
			for(int i=0;i<params.length;i++){
				pstm.setObject(i+1, params[i]);
			}
			rs = pstm.executeQuery();
			String beanName = getBeanName(sql);
			//String className = "com.gotop.beans." + beanName.substring(0,1).toUpperCase()+beanName.substring(1).toLowerCase();
			String className = "com.gotop.vo.system." + beanName.substring(0,1).toUpperCase()+beanName.substring(1).toLowerCase();
			Class cls = Class.forName(className);
			return r2b(rs,cls);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			rs.close();
			pstm.close();
			con.close();
			rs = null;
			pstm = null;
			con = null;
		}
		return null;
	}
	
	public List queryForList(String sql,Object[] params) throws SQLException {
		try {
			this.generURL();
			Class.forName(Driver);
			con = DriverManager.getConnection(url, user, pwd);
			pstm = con.prepareStatement(sql);
			for(int i=0;i<params.length;i++){
				pstm.setObject(i+1, params[i]);
			}
			rs = pstm.executeQuery();
			String beanName = getBeanName(sql);
			//String className = "com.gotop.beans." + beanName.substring(0,1).toUpperCase()+beanName.substring(1).toLowerCase();
			String className = "com.gotop.beans." + beanName.substring(0,1).toUpperCase()+beanName.substring(1).toLowerCase();
			Class cls = Class.forName(className);
			return r2blst(rs,cls);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			rs.close();
			pstm.close();
			con.close();
			rs = null;
			pstm = null;
			con = null;
		}
		return null;
	}
	
	private String getBeanName(String sql){
		sql = sql.toUpperCase();
		int startIndex =  sql.indexOf("FROM");
		String substr = sql.substring(startIndex + 5);
		int spacesFirst = substr.indexOf(" ");
		String returnstr = substr.substring(0,spacesFirst);
		return returnstr;
	}
	
	public Object r2b(ResultSet rs,Class clas){
		try {
			Object beanObj = clas.newInstance();
			Field fields[] = beanObj.getClass().getDeclaredFields();
			rs.next();
			for(int i=0;i<fields.length;i++){
				String fieldName = fields[i].getName().substring(0, 1).toUpperCase() + fields[i].getName().substring(1);
				String type = fields[i].getType().getName();
				String methodName = "set" + fieldName;
				Method method;
				if(type.equals("java.lang.String")){
					method = beanObj.getClass().getDeclaredMethod(methodName,String.class);
					method.invoke(beanObj, rs.getString(fieldName));
				}else if(type.equals("java.lang.Integer") || type.equals("int")){
					method = beanObj.getClass().getDeclaredMethod(methodName,Integer.class);
					method.invoke(beanObj, rs.getInt(fieldName));
				}else if(type.equals("java.util.Date") || type.equals("java.sql.Date")){
					method = beanObj.getClass().getDeclaredMethod(methodName,java.util.Date.class);
					method.invoke(beanObj, rs.getDate(fieldName));
				}else if(type.equals("java.lang.Long") || type.equals("long")){
					method = beanObj.getClass().getDeclaredMethod(methodName,Long.class);
					method.invoke(beanObj, rs.getLong(fieldName));
				}else if(type.equals("java.lang.Short") || type.equals("short")){
					method = beanObj.getClass().getDeclaredMethod(methodName,Short.class);
					method.invoke(beanObj, rs.getShort(fieldName));
				}else if(type.equals("java.lang.Float") || type.equals("float")){
					method = beanObj.getClass().getDeclaredMethod(methodName,Float.class);
					method.invoke(beanObj, rs.getFloat(fieldName));
				}else{
					method = beanObj.getClass().getDeclaredMethod(methodName,Object.class);
					method.invoke(beanObj, rs.getObject(fieldName));
				}
				
			}
			return beanObj;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List r2blst(ResultSet rs,Class clas){
		try {
			Object beanObj = null;
			Object beanType = clas.newInstance();
			Field fields[] = beanType.getClass().getDeclaredFields();
			List lst = new ArrayList();
			while(rs.next()){
				beanObj = clas.newInstance();
				for(int i=0;i<fields.length;i++){
					String fieldName = fields[i].getName().substring(0, 1).toUpperCase() + fields[i].getName().substring(1);
					String type = fields[i].getType().getName();
					String methodName = "set" + fieldName;
					Method method;
					if(type.equals("java.lang.String")){
						method = beanObj.getClass().getDeclaredMethod(methodName,String.class);
						method.invoke(beanObj, rs.getString(fieldName));
					}else if(type.equals("java.lang.Integer") || type.equals("int")){
						method = beanObj.getClass().getDeclaredMethod(methodName,Integer.class);
						method.invoke(beanObj, rs.getInt(fieldName));
					}else if(type.equals("java.util.Date") || type.equals("java.sql.Date")){
						method = beanObj.getClass().getDeclaredMethod(methodName,java.util.Date.class);
						method.invoke(beanObj, rs.getDate(fieldName));
					}else if(type.equals("java.lang.Long") || type.equals("long")){
						method = beanObj.getClass().getDeclaredMethod(methodName,Long.class);
						method.invoke(beanObj, rs.getLong(fieldName));
					}else if(type.equals("java.lang.Short") || type.equals("short")){
						method = beanObj.getClass().getDeclaredMethod(methodName,Short.class);
						method.invoke(beanObj, rs.getShort(fieldName));
					}else if(type.equals("java.lang.Float") || type.equals("float")){
						method = beanObj.getClass().getDeclaredMethod(methodName,Float.class);
						method.invoke(beanObj, rs.getFloat(fieldName));
					}else{
						method = beanObj.getClass().getDeclaredMethod(methodName,Object.class);
						method.invoke(beanObj, rs.getObject(fieldName));
					}
					
				}
				lst.add(beanObj);
			}
			return lst;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
