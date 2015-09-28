package com.gotop.util.convertor;

import com.thoughtworks.xstream.converters.basic.IntConverter;

public class IntConvertor extends IntConverter {
    public Object fromString(String str) {
    	if(str!=null && "".equals(str.toString())){
    		Integer e = -9999;
    		return e;
    	}
        return super.fromString(str);
    }
}
