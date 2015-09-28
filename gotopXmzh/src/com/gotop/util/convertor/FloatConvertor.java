package com.gotop.util.convertor;

import com.thoughtworks.xstream.converters.basic.FloatConverter;

public class FloatConvertor extends FloatConverter {
    public Object fromString(String str) {
    	if(str!=null && "".equals(str.toString())){
    		Float e = -9999F;
    		return e;
    	}
        return super.fromString(str);
    }
}
