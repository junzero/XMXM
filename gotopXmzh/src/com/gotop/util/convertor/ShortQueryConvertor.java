package com.gotop.util.convertor;

import com.thoughtworks.xstream.converters.basic.ShortConverter;

public class ShortQueryConvertor extends ShortConverter{
    public Object fromString(String str) {
    	if(str!=null && "".equals(str.toString())){
    		return null;
    	}
        return super.fromString(str);
    }
}
