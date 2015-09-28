package com.gotop.util.convertor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.thoughtworks.xstream.converters.SingleValueConverter;

public class TimestampsConvertor implements SingleValueConverter
{
    private static DateFormat SIMPLE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public Object fromString(String arg0) {
		try
        {
			if(StringUtils.isBlank(arg0)){
				return null;
			}
			if(arg0.length()==10){
				DateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				return sf.parseObject(arg0);
			}
            Date dt = (Date)SIMPLE_FORMAT.parseObject(arg0);
            return dt;
        }
        catch(Exception e2)
        {
            e2.printStackTrace();
        }
		return null;
	}
	public String toString(Object value) {
        if(value == null || value.equals(new Date(0L)))
        {
            return "";
        } else
        {
            Date dt = (Date)value;
            return SIMPLE_FORMAT.format(dt);
        }
	}
	public boolean canConvert(Class arg0) {
		return Date.class == arg0;
	}

}
