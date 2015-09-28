package com.gotop.util;

import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * <p>Title: 常见的字符串操作和函数封装类</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: 2011</p>
 *
 * <p>Company: GOTOP</p>
 *
 * @author LMY
 *
 * @date 2011-3-4
 *
 * @version 1.0
 **/
public class StringUtil {
	public static Logger logger = Logger.getLogger(StringUtil.class);
	/**
	 * 去除字符串两端的空格，如果为null，则返回空字符串
	 * @param str 要去除空格的字符串
	 * @return 去掉两端空格的字符串
	 */
	public static String trim(String str){
		return (str == null ? "":str.trim()); 
	}
    /**
     * md5散列加密
     * @param str 要加密的字符串
     * @return 加密后的字符串
     */
    public static String encrypt(String str) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                           	'a', 'b', 'c', 'd','e', 'f'};
        try {
            byte[] strTemp = str.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char tmp[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                tmp[k++] = hexDigits[byte0 >>> 4 & 0xf];
                tmp[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(tmp);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    public static void main(String[] args) {
		System.out.println(encrypt("123123"));
		System.out.println("http://www.baidu.com/的MD5加密后：\n"+encrypt("http://www.baidu.com/"));
	}
    /**
     * 转换Double数据类型精度
     * @param value 要转换的数据
     * @param precision 小数点后的位数
     * @return double 转换的数据
     */
    public static double convertPrecision(double value, int precision) {
        String strDoubleValue = String.valueOf(value);
        int len = strDoubleValue.indexOf(".");
        if (len != -1) {
            String integerValue = strDoubleValue.substring(0, len);
            String precisionValue = strDoubleValue.substring(len + 1);
            if (precisionValue.length() > precision) {
                precisionValue = precisionValue.substring(0, precision);
            }
            strDoubleValue = integerValue + "." + precisionValue;
        }
        return Double.parseDouble(strDoubleValue);
    }
    /**
	 * 将字符串中的小写字母转换为大写字母
	 * 
	 * @param s
	 *            输入字符串
	 * @return 返回转换为大写字母后的字符串
	 */
	public static byte[] byteUpper(byte[] s) {
		return (new String(s)).toUpperCase().getBytes();
	}
	
	/**
	 * 将字符串中的大写字母转换为小写字母
	 * 
	 * @param s
	 *            输入字符串
	 * @return 返回转换为小写字母后的字符串
	 */
	public static byte[] byteLower(byte[] s) {
		return (new String(s)).toLowerCase().getBytes();
	}
	
	/**
	 * 查询一个字符串中子串出现的次数
	 * 
	 * @param in_pszMain
	 *            输入的查询字符串
	 * @param in_pszSubstr
	 *            子串
	 * @return 返回子串在源字符串中出现的次数
	 */
	public static int countSubstr(String in_pszMain, String in_pszSubstr) {
		if (in_pszMain == null || in_pszSubstr == null)
			return 0;
		int i = 0;
		int j = 0;
		while (1 == 1) {
			j = in_pszMain.indexOf(in_pszSubstr, j);
			if (j > -1) {
				i++;
				j = j + in_pszSubstr.length();
			} else
				break;
		}
		return i;
	}
	/**
	 * 判断输入IP是否为合法IP
	 * 
	 * @param ip
	 *            输入ip
	 * @param flag
	 *            有无通配符(*)标志，true有，false则无
	 * @return 输入IP，合法返回true,反之则false
	 */
	public static boolean isIP(String ip, boolean flag) {
		String test = "";
		if (flag)
			test = "([1-9]|[1-9]\\d|1\\d{2}|2[0-1]\\d|22[0-3]|\\*)(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5]|\\*)){3}";
		else
			test = "([1-9]|[1-9]\\d|1\\d{2}|2[0-1]\\d|22[0-3])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
		Pattern pattern = Pattern.compile(test);
		Matcher matcher = pattern.matcher(ip);
		return matcher.matches();
	}
	/**
	 * 转换金额到人民币大写金额
	 * 
	 * @param value
	 *            输入金额
	 * @return 返回转换后的结果
	 */
	public static String amtConvUp(double value) {
		char[] hunit = { '拾', '佰', '仟' }; // 段内位置表示
		char[] vunit = { '万', '亿', '万', }; // 段名表示
		char[] digit = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' }; // 数字表示
		long midVal = (long) (value * 100); // 转化成整形
		String valStr = String.valueOf(midVal); // 转化成字符串
		String head = valStr.substring(0, valStr.length() - 2); // 取整数部分
		String rail = valStr.substring(valStr.length() - 2); // 取小数部分

		// 最大支持千万亿级别
		if (head.length() > 16) {
			return "";
		}
		String prefix = ""; // 整数部分转化的结果
		String suffix = ""; // 小数部分转化的结果
		// 处理小数点后面的数
		if (rail.equals("00")) { // 如果小数部分为0
			suffix = "整";
		} else {
			suffix = digit[rail.charAt(0) - '0'] + "角"
					+ digit[rail.charAt(1) - '0'] + "分"; // 否则把角分转化出来
		}
		// 处理小数点前面的数
		char[] chDig = head.toCharArray(); // 把整数部分转化成字符数组
		char zero = '0'; // 标志'0'表示出现过0
		byte zeroSerNum = 0; // 连续出现0的次数
		for (int i = 0; i < chDig.length; i++) { // 循环处理每个数字
			int idx = (chDig.length - i - 1) % 4; // 取段内位置
			int vidx = (chDig.length - i - 1) / 4; // 取段位置
			if (chDig[i] == '0') { // 如果当前字符是0
				zeroSerNum++; // 连续0次数递增
				if (zero == '0') { // 标志
					zero = digit[0];
				}
				if (idx == 0 && vidx > 0 && zeroSerNum < 4) {
					prefix += vunit[vidx - 1];
					zero = '0';
				}
				// 特例,处理万亿整零以上的
				if (idx == 0 && vidx == 2 && zeroSerNum >= 4) {
					prefix += vunit[vidx - 1];
					zero = '0';
				}
				continue;
			}
			zeroSerNum = 0; // 连续0次数清零
			if (zero != '0') { // 如果标志不为0,则加上,例如万,亿什么的
				prefix += zero;
				zero = '0';
			}
			prefix += digit[chDig[i] - '0']; // 转化该数字表示
			if (idx > 0)
				prefix += hunit[idx - 1];
			if (idx == 0 && vidx > 0) {
				prefix += vunit[vidx - 1]; // 段结束位置应该加上段名如万,亿
			}
		}

		if (prefix.length() > 0)
			prefix += '圆'; // 如果整数部分存在,则有圆的字样
		return prefix + suffix; // 返回正确表示

	}
}
