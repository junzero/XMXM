package com.gotop.util;
import java.math.BigDecimal;
/**
 * 类型转换操作类
 */
public final class TypeHelper{

	public static String floatToString(float value)
	{
  	  Float floatee = new Float(value);
	  return floatee.toString();
	}	
	public static float stringToFloat(String floatstr)
	{
	  Float floatee;
	  floatee = Float.valueOf(floatstr);
	  return floatee.floatValue();
	}
	public static Double BigDecimalToDoublc(BigDecimal bigDecimal){
	  if(bigDecimal==null) return null;
	  Double retu=new Double(bigDecimal.toString());
	  return retu;  
	}
}
