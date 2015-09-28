package com.gotop.util.convertor;

import com.thoughtworks.xstream.converters.basic.ShortConverter;

public class ShortConvertor extends ShortConverter{
    public Object fromString(String str) {
    	if(str!=null && "".equals(str.toString())){
    		Short e = -9999;
    		return e;
    	}
        return super.fromString(str);
    }
}
