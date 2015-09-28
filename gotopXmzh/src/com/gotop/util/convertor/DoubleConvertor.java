package com.gotop.util.convertor;

import com.thoughtworks.xstream.converters.basic.DoubleConverter;

public class DoubleConvertor extends DoubleConverter {
    public Object fromString(String str) {
    	if(str!=null && "".equals(str.toString())){
    		Double e = -9999D;
    		return e;
    	}
        return super.fromString(str);
    }
}
