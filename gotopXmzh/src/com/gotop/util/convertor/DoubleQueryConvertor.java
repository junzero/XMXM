package com.gotop.util.convertor;

import com.thoughtworks.xstream.converters.basic.DoubleConverter;

public class DoubleQueryConvertor extends DoubleConverter {
    public Object fromString(String str) {
    	if(str!=null && "".equals(str.toString())){
    		return null;
    	}
        return super.fromString(str);
    }
}
