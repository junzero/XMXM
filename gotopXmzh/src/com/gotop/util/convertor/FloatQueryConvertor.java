package com.gotop.util.convertor;

import com.thoughtworks.xstream.converters.basic.FloatConverter;

public class FloatQueryConvertor extends FloatConverter {
    public Object fromString(String str) {
    	if(str!=null && "".equals(str.toString())){
    		return null;
    	}
        return super.fromString(str);
    }
}
