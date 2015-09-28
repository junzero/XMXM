package com.gotop.util.report;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.garyman.util.DateTimeCU;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class DataSource implements JRDataSource {
	
	private int index = -1;
	private List dataList = null;
	private final static String CLASS_PATH_MAP = "java.util.HashMap";
	
	public DataSource(){
		
	}
	
	public DataSource(List dataList){
		this.dataList = dataList;
	}
	
	public Object getFieldValue(JRField arg0) throws JRException {
		// TODO Auto-generated method stub
		Object result = null;
		Object row = dataList.get(index);
		Object fieldValue = null;
		String rowType = row.getClass().getName();
		String fieldType = arg0.getValueClass().getName();
		String fieldName = arg0.getName().toLowerCase();
		if(rowType.equals(CLASS_PATH_MAP)){
			//MAP类型
			Map map = (Map) row;
			fieldValue = map.get(fieldName);
			if(fieldValue!=null && fieldType.equals("java.lang.String")){
				result = fieldValue.toString();
			}else if(fieldValue!=null && fieldType.equals("java.lang.Integer")){
				result = new Integer(fieldValue.toString());
			}else if(fieldValue!=null && fieldType.equals("java.lang.Short")){
				result = new Integer(fieldValue.toString());
			}else if(fieldValue!=null && fieldType.equals("java.lang.Double")){
				result = new Double(fieldValue.toString());
			}else if(fieldValue!=null && fieldType.equals("java.util.Date")){
				result = java.sql.Timestamp.valueOf(DateTimeCU.toString((java.util.Date)fieldValue, "yyyy-MM-dd"));
			}else{
				if(fieldValue!=null){
					result = fieldValue.toString();
				}else{
					result = "";
				}
			}
		}else{
			//BEAN类型
			try {
				fieldValue = BeanUtils.getProperty(row, fieldName);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(fieldValue!=null && fieldType.equals("java.lang.String")){
				result = fieldValue.toString();
			}else if(fieldValue!=null && fieldType.equals("java.lang.Integer")){
				result = new Integer(fieldValue.toString());
			}else if(fieldValue!=null && fieldType.equals("java.lang.Short")){
				result = new Integer(fieldValue.toString());
			}else if(fieldValue!=null && fieldType.equals("java.lang.Double")){
				result = new Double(fieldValue.toString());
			}else if(fieldValue!=null && fieldType.equals("java.util.Date")){
				result = java.sql.Timestamp.valueOf(DateTimeCU.toString((java.util.Date)fieldValue, "yyyy-MM-dd"));
			}else{
				if(fieldValue!=null){
					result = fieldValue.toString();
				}else{
					result = "";
				}
			}
		}
		return result;
	}

	public boolean next() throws JRException {
		// TODO Auto-generated method stub
		index++;
		return (index < dataList.size());
	}

	public List getDataList() {
		return dataList;
	}

	public void setDataList(List dataList) {
		this.dataList = dataList;
	}
	
}
