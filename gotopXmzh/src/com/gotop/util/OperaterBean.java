package com.gotop.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;

import org.apache.commons.beanutils.BeanUtils;

import com.garyman.util.DateTimeCU;

public class OperaterBean {
	
    public OperaterBean(){
    	
    }	
    
	public static void setFieldValue(String fieldType, String fieldName, String value, Object bean) throws ParseException{
		Object obj = null;
		if (fieldType.equals("N")){
			if(value==null)
				obj = 0;
			else
				obj = new Integer(value);
		} 
		else if (fieldType.equals("D")){
			obj = DateTimeCU.toSQLTimestamp(DateTimeCU.toDate(value, "yyyy-MM-dd"));
		} 			
		else {
			obj = value;
		}		
		setFieldValue(fieldName,obj,bean);
	}    
	
	public static void setFieldValue(String fieldName, Object value, Object bean){
		try{
			BeanUtils.setProperty(bean, fieldName, value);
		}
		catch(Exception e){
			System.out.println(e.toString());			
		}
	}
	
	public static Object getFieldValue(String fieldType, String fieldName, Object bean){
		try {
			if(BeanUtils.getProperty(bean, fieldName)==null) return null;
			if (fieldType.equals("N")){
				Integer obj1 = new Integer(BeanUtils.getProperty(bean,
						fieldName));
				return obj1;
			} 
			else if (fieldType.equals("D")){
				Timestamp obj1 = DateTimeCU.toSQLTimestamp(DateTimeCU.toDate(BeanUtils.getProperty(bean,fieldName), "yyyy-MM-dd"));
				return obj1;
			} 			
			else {
				String obj1 = BeanUtils.getProperty(bean, fieldName);
				return obj1;
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			return "";			
		}			
	}
	
	public static Object getFieldValue(String fieldName, Object bean){
		String fieldType = getType(fieldName,bean);
		return getFieldValue(fieldType, fieldName, bean);
			
	}

    private static String getType(String fieldName, Object bean){
        Field field = null;
        try{
            field = bean.getClass().getDeclaredField(fieldName);
        }
        catch(SecurityException e1){
            return "";
        }
        catch(NoSuchFieldException e1){
            return "";
        }

		if (field.getType().getName().equals(BigDecimal.class.getName())) {
			return "N";
		} 
		else if (field.getType().getName().equals(java.util.Date.class.getName())) {
			return "D";
		} 
		else if (field.getType().getName().equals(java.lang.Integer.class.getName())){
			return "N";
		} 
		else if (field.getType().getName().equals(java.sql.Timestamp.class.getName())){
			return "D";
		} 			
		else {        
			return "C";
		}
    }	
}
