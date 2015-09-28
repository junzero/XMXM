package com.gotop.util.convertor;

import java.math.BigDecimal;

import com.thoughtworks.xstream.converters.basic.BigDecimalConverter;

public class BigDecimalQueryConvertor extends BigDecimalConverter {
    public Object fromString(String str) {
    	if(str!=null && "".equals(str.toString())){
    		return null;
    	}
        return super.fromString(str);
    }
}
