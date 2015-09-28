package com.gotop.util;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Bean处理类
 * @author garyman
 */
public class BeanUtils {
	
	public BeanUtils(){
		
	}
	
	/**
	 * 比较两个结构相同Bean的不相同的字段
	 * @param entity 新实体
	 * @param old 旧实体
	 * @return 返回不相同字段集合
	 */
	public List<Field> equalsBean(Serializable entity,Serializable old){
		java.lang.reflect.Field[] fields = entity.getClass().getDeclaredFields();
		String methodName = "";
		ArrayList<Field> lst = new ArrayList<Field>();
		try{
			for(int i=0;i<fields.length;i++){
				methodName = "get" + fields[i].getName().substring(0, 1).toUpperCase() + fields[i].getName().substring(1);
				Method method = entity.getClass().getDeclaredMethod(methodName);
				Object newvalue = method.invoke(entity);
				Object oldvalue = method.invoke(old);
				if(newvalue!=null && oldvalue!=null){
					if(!newvalue.equals(oldvalue)){
						lst.add(new Field(fields[i].getName(),oldvalue,newvalue));
					}
				}else{
					if(newvalue!=oldvalue){
						lst.add(new Field(fields[i].getName(),oldvalue,newvalue));
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return lst;
	}
	
	public static void copyProperties(Object tar, Object src){
		try {
			BeanUtils.copyProperties(tar,src);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public class Field{
		
		private String fieldName;
		
		private Object oldvalue;
		
		private Object newvalue;
		
		public Field(String fieldName,Object oldvalue,Object newvalue){
			this.fieldName=fieldName;
			this.oldvalue=oldvalue;
			this.newvalue=newvalue;
		}

		public String getFieldName() {
			return fieldName;
		}

		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}

		public Object getOldvalue() {
			return oldvalue;
		}

		public void setOldvalue(Object oldvalue) {
			this.oldvalue = oldvalue;
		}

		public Object getNewvalue() {
			return newvalue;
		}

		public void setNewvalue(Object newvalue) {
			this.newvalue = newvalue;
		}
		
		
	}

}
