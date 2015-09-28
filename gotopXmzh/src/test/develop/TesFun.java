package test.develop;

import java.util.ArrayList;
import java.util.List;


public class TesFun {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List list = new ArrayList();
		String str = "select * from tuser where usid = :usid or gmzq =:gmzq or :usid";
		char[] tca = str.toCharArray();
		for (int i = 0; i < tca.length; i++) {
			char t = tca[i];
			if(t == ':'){
				StringBuffer sbf = new StringBuffer();
				int j = i+1;
				for (; j < tca.length; j++) {
					if ((tca[j] >= '0' && tca[j] <= '9') 
							|| (tca[j] >= 'a' && tca[j] <= 'z') 
							|| (tca[j] >= 'A' && tca[j] <= 'Z')
							|| tca[j] >= '_') {
						sbf.append(tca[j]);
					}else{
						break;
					}
				}
				i = j;
				list.add(sbf);
			}
		}
		System.out.println(list);
	}
}
