package com.gotop.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class SingleValueDateConverter implements Converter {
	DateFormat df=new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
	public void marshal(Object source, HierarchicalStreamWriter writer,
			MarshallingContext context) {
		Date calendar = (Date) source;
		if(calendar==null){
			writer.setValue(null);
		}else{
			writer.setValue(df.format(calendar));
		}
	}

	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {
		Date calendar = null;
		try {
			if(StringUtils.isNotBlank(reader.getValue())){
				calendar = df.parse(reader.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return calendar;
	}

	@SuppressWarnings("unchecked")
	public boolean canConvert(Class type) {
		return type.equals(Date.class);
	}
}
