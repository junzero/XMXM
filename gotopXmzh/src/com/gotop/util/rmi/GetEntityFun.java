package com.gotop.util.rmi;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class GetEntityFun {
	public static String copyFileToBean(Object cob,String objName,String fromName,String selectKey,Object... objects){
		if(cob==null || objects==null){
			return "";
		}
		StringBuffer codeSbr = new StringBuffer();
		String[] fromArra = fromName.split(",");
		for(int i=0;i<objects.length;i++){
			Object obj = objects[i];
			String fname = fromArra[i];
			if(obj instanceof Map){
				if(cob instanceof Map){
					codeSbr.append(mapToMap((Map)cob,objName,(Map)obj,fname,selectKey));
				}else{
					codeSbr.append(beanToMap(cob,objName,(Map)obj,fname,selectKey));
				}
			}else if(obj.getClass().getName().startsWith("com.gotop.")){
				if(cob instanceof Map){
					codeSbr.append(mapToBean((Map)cob,objName,obj,fname,selectKey));
				}else{
					codeSbr.append(beanToBean(cob,objName,obj,fname,selectKey));
				}
			}
		}
		return codeSbr.toString();
	}
	private static String mapToBean(Map hmp,String mapName,Object cob,String objName,String selectKey){
		if(hmp==null || cob==null){
			return null;
		}
		Set set = hmp.keySet();
		Class tCob = cob.getClass();
		Field[] fields = tCob.getDeclaredFields();
		Method[] methods = tCob.getDeclaredMethods();
		StringBuffer filesb = new StringBuffer();
		StringBuffer setsb = new StringBuffer();
		List<String> gsk = getSelectKey(mapName,selectKey);
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			String fname = field.getName();
			Method fmethod = null;
			String mapKey = null;
			//判断是否为需要复制的范围
			if(gsk!=null && !gsk.contains(fname)){
				continue;
			}
			//判断类是否存在
			for (Iterator iterator = set.iterator(); iterator.hasNext();) {
				String name = (String) iterator.next();
				if(fname.equals(name)){
					mapKey = name;
					break;
				}
			}
			if(mapKey==null){
				continue;
			}
			for (int j = 0; j < methods.length; j++) {
				Method method = methods[j];
				if(method.getName().equalsIgnoreCase("set"+fname)){
					fmethod = method;
					break;
				}
			}
			if(fmethod!=null){
				Class type = fmethod.getParameterTypes()[0];
				String tp =getTypeOfMap(hmp,mapKey);
				filesb.append(tp+" "+mapKey+"Str = ("+tp+")"+mapName+".get(\""+mapKey+"\");\n");
				if(type==String.class){
					setsb.append(objName+"."+fmethod.getName()+"("+mapKey+"Str==null?\"\":"+mapKey+"Str);\n");
				}else if(type==Date.class){
					if("Date".equals(tp)){
						setsb.append(objName+"."+fmethod.getName()+"("+mapKey+"Str);\n");
					}else{
						setsb.append(objName+"."+fmethod.getName()+"(TimeUtil.getDate("+mapKey+"Str));\n");
					}
				}else if(type==Long.class){
					if("Long".equals(tp)){
						setsb.append(objName+"."+fmethod.getName()+"("+mapKey+"Str);\n");
					}else{
						setsb.append(objName+"."+fmethod.getName()+"(Long.valueOf("+mapKey+"Str));\n");
					}
				}else if(type==Double.class){
					if("Double".equals(tp)){
						setsb.append(objName+"."+fmethod.getName()+"("+mapKey+"Str);\n");
					}else{
						setsb.append(objName+"."+fmethod.getName()+"(Double.valueOf("+mapKey+"Str));\n");
					}
				}else if(type==BigDecimal.class){
					if("BigDecimal".equals(tp)){
						setsb.append(objName+"."+fmethod.getName()+"("+mapKey+"Str);\n");
					}else{
						setsb.append(objName+"."+fmethod.getName()+"(BigDecimal.valueOf("+mapKey+"Str));\n");
					}
				}else if(type==Integer.class){
					if("Integer".equals(tp)){
						setsb.append(objName+"."+fmethod.getName()+"("+mapKey+"Str);\n");
					}else{
						setsb.append(objName+"."+fmethod.getName()+"(Integer.valueOf("+mapKey+"Str));\n");
					}
				}else{
					setsb.append(objName+"."+fmethod.getName()+"("+mapKey+"Str);\n");
				}
			}
		}
		System.out.println(filesb.toString());
		System.out.println(setsb.toString());
		return filesb.toString()+setsb.toString();
	}
	
	private static String beanToBean(Object hmp,String mapName,Object cob,String objName,String selectKey){
		if(hmp==null || cob==null){
			return null;
		}
		Class tCob = cob.getClass();
		Field[] fieldHmp = hmp.getClass().getDeclaredFields();
		Method[] methodsHmp = tCob.getDeclaredMethods();
		Field[] fields = tCob.getDeclaredFields();
		Method[] methods = tCob.getDeclaredMethods();
		StringBuffer filesb = new StringBuffer();
		StringBuffer setsb = new StringBuffer();
		List<String> gsk = getSelectKey(mapName,selectKey);
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			String fname = field.getName();
			Method fmethod = null;
			Method fMapMethod = null;
			String mapKey = null;
			//判断是否为需要复制的范围
			if(gsk!=null && !gsk.contains(fname)){
				continue;
			}
			//判断类是否存在
			for (int k=0;k<fieldHmp.length;k++) {
				String fMapName = fieldHmp[k].getName();
				if(fname.equals(fMapName)){
					mapKey = fMapName;
					break;
				}
			}
			if(mapKey==null){
				continue;
			}
			//辅值办法
			for (int j = 0; j < methods.length; j++) {
				Method method = methods[j];
				if(method.getName().equalsIgnoreCase("set"+fname)){
					fmethod = method;
					break;
				}
			}
			//获值办法
			for (int j = 0; j < methodsHmp.length; j++) {
				Method method = methodsHmp[j];
				if(method.getName().equalsIgnoreCase("get"+fname)){
					fMapMethod = method;
					break;
				}
			}
			if(fmethod!=null && fMapMethod!=null){
				Class type = fmethod.getParameterTypes()[0];
				String tp =getTypeOfBean(hmp,fname);
				filesb.append(tp+" "+mapKey+"Str = ("+tp+")"+mapName+"."+fMapMethod.getName()+"();\n");
				if(type==String.class){
					setsb.append(objName+"."+fmethod.getName()+"("+mapKey+"Str==null?\"\":"+mapKey+"Str);\n");
				}else if(type==Date.class){
					if("Date".equals(tp)){
						setsb.append(objName+"."+fmethod.getName()+"("+mapKey+"Str);\n");
					}else{
						setsb.append(objName+"."+fmethod.getName()+"(TimeUtil.getDate("+mapKey+"Str));\n");
					}
				}else if(type==Long.class){
					if("Long".equals(tp)){
						setsb.append(objName+"."+fmethod.getName()+"("+mapKey+"Str);\n");
					}else{
						setsb.append(objName+"."+fmethod.getName()+"(Long.valueOf("+mapKey+"Str));\n");
					}
				}else if(type==Double.class){
					if("Double".equals(tp)){
						setsb.append(objName+"."+fmethod.getName()+"("+mapKey+"Str);\n");
					}else{
						setsb.append(objName+"."+fmethod.getName()+"(Double.valueOf("+mapKey+"Str));\n");
					}
				}else if(type==BigDecimal.class){
					if("BigDecimal".equals(tp)){
						setsb.append(objName+"."+fmethod.getName()+"("+mapKey+"Str);\n");
					}else{
						setsb.append(objName+"."+fmethod.getName()+"(BigDecimal.valueOf("+mapKey+"Str));\n");
					}
				}else if(type==Integer.class){
					if("Integer".equals(tp)){
						setsb.append(objName+"."+fmethod.getName()+"("+mapKey+"Str);\n");
					}else{
						setsb.append(objName+"."+fmethod.getName()+"(Integer.valueOf("+mapKey+"Str));\n");
					}
				}else{
					setsb.append(objName+"."+fmethod.getName()+"("+mapKey+"Str);\n");
				}
			}
		}
		System.out.println(filesb.toString());
		System.out.println(setsb.toString());
		return filesb.toString()+setsb.toString();
	}
	
	private static String beanToMap(Object cob,String objName,Map hmp,String mapName,String selectKey){
		if(hmp==null || cob==null){
			return null;
		}
		Class tCob = cob.getClass();
		Field[] fields = tCob.getDeclaredFields();
		Method[] methods = tCob.getDeclaredMethods();
		StringBuffer filesb = new StringBuffer();
		StringBuffer setsb = new StringBuffer();
		List<String> gsk = getSelectKey(objName,selectKey);
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			String fname = field.getName();
			Method fmethod = null;
			//判断是否为需要复制的范围
			if(gsk!=null && !gsk.contains(fname)){
				continue;
			}
			//判断类是否存在
			for (int j = 0; j < methods.length; j++) {
				Method method = methods[j];
				if(method.getName().equalsIgnoreCase("get"+fname)){
					fmethod = method;
					break;
				}
			}
			
			if(fmethod==null){
				continue;
			}
			if(fmethod!=null){
				filesb.append(mapName+".put(\""+fname+"\","+objName+"."+fmethod.getName()+"());\n");
			}
		}
		System.out.println(filesb.toString());
		System.out.println(setsb.toString());
		return filesb.toString()+setsb.toString();
	}
	
	private static String mapToMap(Map hmp,String mapName,Map cob,String objName,String selectKey){
		if(hmp==null || cob==null){
			return null;
		}
		List<String> gsk = getSelectKey(mapName,selectKey);
		StringBuffer filesb = new StringBuffer();
		Set<String> kSet = hmp.keySet();
		for (Iterator<String> iterator = kSet.iterator(); iterator.hasNext();) {
			String keyName = (String) iterator.next();
			//判断是否为需要复制的范围
			if(gsk!=null && !gsk.contains(keyName)){
				continue;
			}
			filesb.append(objName+".put(\""+keyName+"\","+mapName+".get(\""+keyName+"\")"+");\n");
		}
//		System.out.println(filesb.toString());
		return filesb.toString();
	}
	/**
	 * 通过bean的key获取类型
	 * @param hm
	 * @param key
	 * @return
	 */
	private static String getTypeOfMap(Map hm,String key){
		Object obj = hm.get(key);
		if(obj==null){
			return "String";
		}else if(obj.getClass()==String.class){
			return "String";
		}else if(obj.getClass()==Date.class){
			return "Date";
		}else if(obj.getClass()==Long.class){
			return "Long";
		}else if(obj.getClass()==Double.class){
			return "Double";
		}else if(obj.getClass()==BigDecimal.class){
			return "BigDecimal";
		}else if(obj.getClass()==Integer.class){
			return "Integer";
		}else{
			return "String";
		}
	}
	/**
	 * 通过bean的字段获取类型
	 * @param bean
	 * @param fieldName
	 * @return
	 */
	private static String getTypeOfBean(Object bean,String fieldName){
		Field[] fields = bean.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if(fieldName.equals(field.getName())){
				Class type = field.getType();
				if(type==String.class){
					return "String";
				}else if(type==Date.class){
					return "Date";
				}else if(type==Long.class){
					return "Long";
				}else if(type==Double.class){
					return "Double";
				}else if(type==BigDecimal.class){
					return "BigDecimal";
				}else if(type==Integer.class){
					return "Integer";
				}else if(type==int.class){
					return "int";
				}else if(type==long.class){
					return "long";
				}else if(type==double.class){
					return "double";
				}else{
					return "String";
				}
			}
		}
		return "String";
	}
	
	private static List<String> getSelectKey(String objName,String selectKey){
		if(selectKey==null){
			return null;
		}
		List<String> result = new ArrayList<String>();
		String[] sk = selectKey.split(",");
		for (int i = 0; i < sk.length; i++) {
			if(sk[i]==null){
				continue;
			}
			if(sk[i].startsWith(objName+".")){
				String aName = sk[i];
				if(sk[i].lastIndexOf(".")>-1){
					aName = sk[i].substring(sk[i].lastIndexOf(".")+1);
				}
				result.add(aName);
			}
		}
		return result;
	}
	public static String funname(Object obj){
		if(obj==null){
			return null;
		}
		List<DebugEntity> result = new ArrayList<DebugEntity>();
		if(obj instanceof List){//列表
			List ltObj = (List)obj;
			if(ltObj.size()<1){
				return null;
			}else{
				obj = ltObj.get(0);
			}
		}else if(obj.getClass().isArray()){//数组需重取
			Object[] objt = (Object[])obj;
			if(objt.length<1 || objt[0]==null){
				return null;
			}
			obj = objt[0];
		}
		if(obj instanceof HttpServletRequest || obj.getClass().getName().equals("org.apache.struts2.dispatcher.StrutsRequestWrapper")){//接收提交而来的数据
			HttpServletRequest request=(HttpServletRequest)obj;
			Enumeration test=request.getParameterNames();
			while(test.hasMoreElements()){
				DebugEntity de =new DebugEntity();
				String name=(String)test.nextElement();
				de.setName(name);
				de.setType(Types.VARCHAR);
				result.add(de);
			}
		}else if(obj instanceof Enumeration){//枚举
			Enumeration test= (Enumeration) obj;
			while(test.hasMoreElements()){
				DebugEntity de =new DebugEntity();
				String name=(String)test.nextElement();
				de.setName(name);
				de.setType(Types.VARCHAR);
				result.add(de);
			}
		}else if(obj instanceof Set){//仅适用于Set<String>
			Set test= (Set) obj;
			for (Iterator iterator = test.iterator(); iterator.hasNext();) {
				String name = iterator.next().toString();
				DebugEntity de =new DebugEntity();
				de.setName(name);
				de.setType(Types.VARCHAR);
				result.add(de);
			}
		}else if(obj instanceof Iterator){//枚举
			for (Iterator iterator = (Iterator)obj; iterator.hasNext();) {
				String name = (String) iterator.next();
				DebugEntity de =new DebugEntity();
				de.setName(name);
				de.setType(Types.VARCHAR);
				result.add(de);
			}
		}else if(obj instanceof Map){//仅适用于Map<String,Object>
			Map test = (Map)obj;
			Set set = test.keySet();
			for (Iterator iterator = set.iterator(); iterator.hasNext();) {
				String name = iterator.next().toString();
				DebugEntity de =new DebugEntity();
				Object value = test.get(name);
				de.setName(name);
				de.setType(JavaObjectToJdbc(value));
				result.add(de);
			}
		}else if(obj.getClass().getName().startsWith("com.gotop.")){//仅适用于Map<String,Object>
			Class test = obj.getClass();
			Field[] fields = test.getDeclaredFields();
			Method[] methods = test.getDeclaredMethods();
			Set<String> methodName = new HashSet<String>();
			for (int k = 0; k < methods.length; k++) {
				String name = methods[k].getName();
				if(name.startsWith("get") && methods[k].getParameterTypes().length<1 && methods[k].getReturnType()!=null){
					methodName.add(name.substring(3).toUpperCase());
				}
			}
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				String fieldStr = field.getName();
				if(methodName.contains(field.getName().toUpperCase())){
					DebugEntity de =new DebugEntity();
					de.setName(fieldStr);
					de.setType(JavaObjectToJdbc(field.getType()));
					result.add(de);
				}
			}
		}
		XStream sm = new XStream(new DomDriver()); //创建Xstream对象   
		sm.autodetectAnnotations(true);
		String xml = sm.toXML(result);
		System.out.println("读取到的信息："+xml);
		return xml;
	}
	public static String getAllPruned(String nKey,Object... rootAll){
		if(rootAll==null || nKey==null){
			return null;
		}
		List<DebugEntity> result = new ArrayList<DebugEntity>();
		String[] nKeyArray = nKey.split(",");
		for (int i = 0; i < rootAll.length; i++) {
			Object obj = rootAll[i];
			if(obj==null){//不能为空
				continue;
			}
			if(obj instanceof Map || obj.getClass().getName().startsWith("com.gotop.")){
				DebugEntity de =new DebugEntity();
				de.setName(nKeyArray[i]);
				de.setType(JavaObjectToJdbc(obj));
				result.add(de);
			}else{//不能为空
				continue;
			}
		}
		XStream sm = new XStream(new DomDriver()); //创建Xstream对象   
		sm.autodetectAnnotations(true);
		String xml = sm.toXML(result);
		System.out.println("------读取到的全部信息："+xml);
		return xml;
	}
	/**
	 * jdbc转化为object类型
	 * @param tyeps
	 * @return
	 */
	public static String JdbcToJavaObject(Integer tyeps){
		switch (tyeps) {
			case Types.CHAR:
			case Types.VARCHAR:
			case Types.LONGVARCHAR:
			case Types.NULL:
			case -8:
				return "String";
			case Types.NUMERIC:
			case Types.DECIMAL:
				return "java.math.BigDecimal";
			case Types.BIT:
				return "Boolean";
			case Types.TINYINT:
			case Types.SMALLINT:
			case Types.INTEGER:
				return "Integer";
			case Types.BIGINT:
				return "Long";
			case Types.REAL:
				return "Float";
			case Types.FLOAT:
			case Types.DOUBLE:
				return "Double";
			case Types.BINARY:
			case Types.VARBINARY:
			case Types.LONGVARBINARY:
				return "byte[]";
			case Types.DATE:
				return "java.util.Date";
			case Types.TIME:
				return "java.sql.Time";
			case Types.TIMESTAMP:
				return "java.sql.Timestamp";
			default:
				break;
		}
		return null;
	}
	/**
	 * java对象转化为jdbc
	 * @param obj
	 * @return
	 */
	public static Integer JavaObjectToJdbc(Object obj){
		if(obj==null){
			return Types.VARCHAR;
		}else if(obj instanceof String){
			return Types.VARCHAR;
		}else if(obj instanceof BigDecimal){
			return Types.DECIMAL;
		}else if(obj instanceof Integer){
			return Types.INTEGER;
		}else if(obj instanceof Long){
			return Types.BIGINT;
		}else if(obj instanceof Float){
			return Types.REAL;
		}else if(obj instanceof Double){
			return Types.DOUBLE;
		}else if(obj instanceof byte[]){
			return Types.VARBINARY ;
		}else if(obj instanceof Date){
			return Types.DATE ;
		}else if(obj instanceof java.sql.Time){
			return Types.TIME;
		}else if(obj instanceof java.sql.Timestamp){
			return Types.TIMESTAMP;
		}
		return Types.VARCHAR;
	}
	/**
	 * java类转化为jdbc
	 * @param obj
	 * @return
	 */
	public static Integer JavaObjectToJdbc(Class obj){
		if(obj==null){
			return Types.VARCHAR;
		}else if(obj == String.class){
			return Types.VARCHAR;
		}else if(obj == BigDecimal.class){
			return Types.DECIMAL;
		}else if(obj == Integer.class){
			return Types.INTEGER;
		}else if(obj == Long.class){
			return Types.BIGINT;
		}else if(obj == Float.class){
			return Types.REAL;
		}else if(obj == Double.class){
			return Types.DOUBLE;
		}else if(obj == byte[].class){
			return Types.VARBINARY ;
		}else if(obj == Date.class){
			return Types.DATE ;
		}else if(obj == java.sql.Time.class){
			return Types.TIME;
		}else if(obj == java.sql.Timestamp.class){
			return Types.TIMESTAMP;
		}
		return Types.VARCHAR;
	}
	/**
	 * java对象转化为jdbc 文本
	 * @param obj
	 * @return
	 */
	public static String JavaObjectToJdbcStr(int types){
		if(types==Types.VARCHAR){
			return "VARCHAR";
		}else if(types==Types.VARCHAR){
			return "CHAR";
		}else if(types==Types.DECIMAL){
			return "DECIMAL";
		}else if(types==Types.INTEGER){
			return "INTEGER";
		}else if(types==Types.BIGINT){
			return "BIGINT";
		}else if(types==Types.REAL){
			return "REAL";
		}else if(types==Types.DOUBLE){
			return "DOUBLE";
		}else if(types==Types.VARBINARY){
			return "VARBINARY";
		}else if(types==Types.DATE){
			return "DATE";
		}else if(types==Types.TIME){
			return "TIME";
		}else if(types==Types.TIMESTAMP){
			return "TIMESTAMP";
		}
		return "VARCHAR";
	}
	public static void main(String[] arg){
		HashMap<String,Object> hmp = new HashMap<String,Object> ();
		hmp.put("a1", "a1");
		hmp.put("a2", 2);
		hmp.put("a3", new Date());
		hmp.put("vCxh", new Date());
		
		/*String funStr = funname(hmp);
		System.out.println(funStr);*/
		
		HashMap<String,Object> hmp2 = new HashMap<String,Object> ();
//		TestEntity ten1 = new TestEntity();
//		TestEntity ten2 = new TestEntity();
//      -------------MapTOMap---------------		
//		String selectKey = "hmp.a1,hmp.a3";
//		String result = GetEntityFun.copyFileToBean(hmp,"hmp","hmp2",selectKey,hmp2);
//		System.out.println("--result-\n"+result);
		
//      -------------BeanTOMap---------------	
//		String selectKey = "ten1.vCxh,ten1.a3";
//		String result = GetEntityFun.copyFileToBean(ten1,"ten1","hmp2",selectKey,hmp2);
//		System.out.println("--result-\n"+result);
		
//      -------------MapTOBean---------------		
//		String selectKey = "hmp.vCxh,hmp.a3";
//		String result = GetEntityFun.copyFileToBean(hmp,"hmp","ten1",selectKey,ten1);
//		System.out.println("--result-\n"+result);
	
//      -------------BeanTOBean---------------
//		String selectKey = "ten2.vCxh,ten2.fSxf";
//		String result = GetEntityFun.copyFileToBean(ten2,"ten2","ten1",selectKey,ten1);
//		System.out.println("--result-\n"+result);
		
		
//      -------------混合多项---------------
		String selectKey = "ten2.vCxh,ten2.dint";
//		String result = GetEntityFun.copyFileToBean(ten2,"ten2","ten1,hmp",selectKey,ten1,hmp);
//		String result = GetEntityFun.copyFileToBean(ten1,"ten1","ten2","ten1.fSxf,ten1.dint,ten1.dInteger",ten2);
//		System.out.println("--result-\n"+result);
	}
}
