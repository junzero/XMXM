package com.gotop.util.convertor;

import com.thoughtworks.xstream.converters.basic.LongConverter;

public class LongConvertor extends LongConverter
{
    public Object fromString(String str) {
    	if(str!=null && "".equals(str.toString())){
    		Long e = -9999L;
    		return e;
    	}
        return super.fromString(str);
    }
}
