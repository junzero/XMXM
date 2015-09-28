// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   StringUtil.java

package com.eos.web.taglib.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil
{

    public StringUtil()
    {
    }

    public static List splitString(String str)
    {
        return splitString(str, ",");
    }

    public static List splitString(String str, String seperator)
    {
        if(str == null)
            return new ArrayList(1);
        if(seperator == null)
            seperator = ",";
        ArrayList retlist = new ArrayList(5);
        if(seperator.length() == 0)
        {
            retlist.add(str);
            return retlist;
        }
        int sepLen = seperator.length();
        for(int pos = str.indexOf(seperator); pos != -1; pos = str.indexOf(seperator))
        {
            retlist.add(str.substring(0, pos));
            str = str.substring(pos + sepLen, str.length());
        }

        retlist.add(str);
        return retlist;
    }

    public static String htmlFilter(String value)
    {
        if(value == null || value.length() == 0)
            return value;
        StringBuilder result = null;
        String filtered = null;
        for(int i = 0; i < value.length(); i++)
        {
            filtered = null;
            switch(value.charAt(i))
            {
            case 60: // '<'
                filtered = "&lt;";
                break;

            case 62: // '>'
                filtered = "&gt;";
                break;

            case 38: // '&'
                filtered = "&amp;";
                break;

            case 34: // '"'
                filtered = "&quot;";
                break;

            case 39: // '\''
                filtered = "&#39;";
                break;
            }
            if(result == null)
            {
                if(filtered == null)
                    continue;
                result = new StringBuilder(value.length() + 50);
                if(i > 0)
                    result.append(value.substring(0, i));
                result.append(filtered);
                continue;
            }
            if(filtered == null)
                result.append(value.charAt(i));
            else
                result.append(filtered);
        }

        return result != null ? result.toString() : value;
    }

    public static String getXpathNameWithIndex(String xpath, int index)
    {
    	if(index>0){
    		index=index - 1 ;
    	}
        if(xpath.indexOf("[*]") > 0)
            return xpath.replaceFirst("\\[\\*\\]", (new StringBuilder()).append("[").append(index).append("]").toString());
        Pattern pattern = Pattern.compile("\\[\\d+\\]");
        Matcher matcher = pattern.matcher(xpath);
        if(matcher.find())
            return xpath;
        String[] arr = xpath.split("\\.");
        if(arr.length <2 && xpath.indexOf("/")>0){
        	arr = xpath.split("/");
        }
        int length = arr.length;
        if(arr.length == 1)
            return (new StringBuilder()).append(xpath).append("[").append(index).append("]").toString();
        if(arr.length > 1)
        {
            StringBuilder buffer = new StringBuilder();
            for(int i = 0; i < arr.length; i++)
            {
                buffer.append(arr[i]);
                if(i == 0)
                    buffer.append("[").append(index).append("]");
                if(i != arr.length - 1)
                    buffer.append(".");
            }

            return buffer.toString();
        } else
        {
            return xpath;
        }
    }

    public static String replace(String text, String repl, String with)
    {
        return replace(text, repl, with, -1);
    }

    public static String replace(String text, String repl, String with, int max)
    {
        if(text == null || repl == null || with == null || repl.length() == 0 || max == 0)
            return text;
        StringBuilder buf = new StringBuilder(text.length());
        int start = 0;
        int end = 0;
        do
        {
            if((end = text.indexOf(repl, start)) == -1)
                break;
            buf.append(text.substring(start, end)).append(with);
            start = end + repl.length();
        } while(--max != 0);
        buf.append(text.substring(start));
        return buf.toString();
    }
}
