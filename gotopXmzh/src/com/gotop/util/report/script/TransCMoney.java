package com.gotop.util.report.script;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.jasperreports.engine.JRDefaultScriptlet;

//ireport引入java类说明：
/**
 * 0、此类必须继承JRDefaultScriptlet。方法是static的
 * 1、将此类打包成jar文件。（选中此类不是项目）
 * 2、再Ireport的Class路径里导入此jar文件
 * 3、在Scriptlets - REPORT 属性中。Scriptlet Class 项中输入此类的名称
 * 4、在报表的属性中找到 when no Date 选择 All Sections,No detail 项（意识是页面为null继续显示）
 * 5、添加个Variables变量，对Text Field Expression 中重新设置，
 *    选择Parameters中的REPORT_SCRIPTLET参数，然后右边会将导入的类的方法名显示出来，选择即可
 * 
 * 
 */

public class TransCMoney{
	
	/**
      * 获得金额的汉字大写格式 <br>
      * @param money 小写金额字符串
      * @return 大写的汉字金额
      */
     public static String getCMoney(String moneys) {
         String text = tCM1(moneys) + tCM2(moneys);
         Pattern p = Pattern.compile("零分", Pattern.CASE_INSENSITIVE);
         Matcher m = p.matcher(text);
         text = m.replaceAll("");
         return text;
     }
     /**
      * 获得金额的汉字大写格式 <br>
      * @param money 小写金额字符串
      * @return 大写的汉字金额
      */
     public static String getCMoney(Integer moneys) {
    	 String text = getCMoney(String.valueOf(moneys));
    	 return text;
     }
     /**
      * 获得金额的汉字大写格式 <br>
      * @param money 小写金额字符串
      * @return 大写的汉字金额
      */
     public static String getCMoney(int moneys) {
    	 String text = getCMoney(String.valueOf(moneys));
    	 return text;
     }
	
	/**
	 * 截得输入金额的整数部分，并将其转换成中文大写的格式 <br>
	 * <br>
	 * 其他描述：输入数字超过接受范围时拒绝转换并退出。<br>
	 * 
	 * @param 传递参数字符串S
	 *            参数描述
	 * @return 返回转换后的字符串
	 */
	public static String tCM1(String s) {
		String ss = s;
		String tmpnewchar = "";
		String[] part = ss.split("\\.");

		if (part[0].length() > 10) {
			// 超出可转换位数
			return "";
		}
		for (int i = 0; i < part[0].length(); i++) {
			char perchar = part[0].charAt(i);
			if (perchar == '0')
				tmpnewchar = tmpnewchar + "零";
			if (perchar == '1')
				tmpnewchar = tmpnewchar + "壹";
			if (perchar == '2')
				tmpnewchar = tmpnewchar + "贰";
			if (perchar == '3')
				tmpnewchar = tmpnewchar + "叁";
			if (perchar == '4')
				tmpnewchar = tmpnewchar + "肆";
			if (perchar == '5')
				tmpnewchar = tmpnewchar + "伍";
			if (perchar == '6')
				tmpnewchar = tmpnewchar + "陆";
			if (perchar == '7')
				tmpnewchar = tmpnewchar + "柒";
			if (perchar == '8')
				tmpnewchar = tmpnewchar + "捌";
			if (perchar == '9')
				tmpnewchar = tmpnewchar + "玖";

			int j = part[0].length() - i - 1;
			if (j == 0)
				tmpnewchar = tmpnewchar + "圆";
			if (j == 1 && perchar != 0)
				tmpnewchar = tmpnewchar + "拾";
			if (j == 2 && perchar != 0)
				tmpnewchar = tmpnewchar + "佰";
			if (j == 3 && perchar != 0)
				tmpnewchar = tmpnewchar + "仟";
			if (j == 4 && perchar != 0)
				tmpnewchar = tmpnewchar + "万";
			if (j == 5 && perchar != 0)
				tmpnewchar = tmpnewchar + "拾";
			if (j == 6 && perchar != 0)
				tmpnewchar = tmpnewchar + "佰";
			if (j == 7 && perchar != 0)
				tmpnewchar = tmpnewchar + "仟";
			if (j == 8 && perchar != 0)
				tmpnewchar = tmpnewchar + "亿";
			if (j == 9 && perchar != 0)
				tmpnewchar = tmpnewchar + "拾";
		}
		return tmpnewchar;
	}

	/** */
	/**
	 * 截得输入金额的小数部分，并将其转换成中文大写的格式 <br>
	 * <br>
	 * 其他描述：小数部分超过两位时系统自动截断。<br>
	 * 
	 * @param 传递参数字符串
	 * 
	 * @return 返回转换后的字符串
	 */
	public static String tCM2(String s) {
		String ss = s;
		String tmpnewchar1 = "";
		String[] part = ss.split("\\.");

		if (ss.indexOf(".") != -1) {
			if (part[1].length() > 2) {
				// MessageDialog.openInformation(null,"提示","小数点之后只能保留两位,系统将自动截段");
				part[1] = part[1].substring(0, 2);
			}
			for (int i = 0; i < part[1].length(); i++) {
				char perchar = part[1].charAt(i);
				if (perchar == '0')
					tmpnewchar1 = tmpnewchar1 + "零";
				if (perchar == '1')
					tmpnewchar1 = tmpnewchar1 + "壹";
				if (perchar == '2')
					tmpnewchar1 = tmpnewchar1 + "贰";
				if (perchar == '3')
					tmpnewchar1 = tmpnewchar1 + "叁";
				if (perchar == '4')
					tmpnewchar1 = tmpnewchar1 + "肆";
				if (perchar == '5')
					tmpnewchar1 = tmpnewchar1 + "伍";
				if (perchar == '6')
					tmpnewchar1 = tmpnewchar1 + "陆";
				if (perchar == '7')
					tmpnewchar1 = tmpnewchar1 + "柒";
				if (perchar == '8')
					tmpnewchar1 = tmpnewchar1 + "捌";
				if (perchar == '9')
					tmpnewchar1 = tmpnewchar1 + "玖";

				if (i == 0 && perchar != 0)
					tmpnewchar1 = tmpnewchar1 + "角";
				if (i == 1 && perchar != 0)
					tmpnewchar1 = tmpnewchar1 + "分";
			}
		}
		return tmpnewchar1;
	}


}
