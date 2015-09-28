package com.gotop.util.string;

import java.lang.reflect.Array;

public class Obj2StrUtils {
	/**
	 * 把对象数组的内容用引号与指定分隔符连接
	 * @param <T> 
	 * @param array 对象数组
	 * @param arrayClass 真正的类型
	 * @param split 分割符
	 * @return
	 * @throws Exception
	 */
	public static <T> String join(T[] array,Class arrayClass,String split) throws Exception{
		int size = array.length;
		 T[] t = (T[])Array.newInstance(arrayClass, size);
		StringBuffer roleBuffer = new StringBuffer();
		for(int i = 0; i < size; i++){
			t[i] = array[i];
			if(i == 0){
				roleBuffer.append("'").append(t[i]).append("'");
			}else{
				roleBuffer.append(split).append("'").append(t[i]).append("'");
			}
		}
		return roleBuffer.toString();
	}
}
