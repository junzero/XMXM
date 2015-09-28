package com.gotop.util.convertor;

import com.thoughtworks.xstream.converters.basic.IntConverter;

public class IntQueryConvertor extends IntConverter {
    public Object fromString(String str) {
    	if(str!=null && "".equals(str.toString())){
    		return null;
    	}
        return super.fromString(str);
    }
}
