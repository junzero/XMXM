package test.develop.ibatis;

import java.util.HashMap;

import ognl.Ognl;
import ognl.OgnlException;

public class OgnlTest{
	public static void main(String[] arg){
		HashMap hm = new HashMap();
		hm.put("a1", 11);
		try {
			Object o = Ognl.getValue("a1", hm);
			System.out.println(o);
		} catch (OgnlException e) {
			e.printStackTrace();
		}
	}
	
}
