package com.gotop.util.convertor;

import java.math.BigDecimal;

import com.thoughtworks.xstream.converters.basic.BigDecimalConverter;

public class BigDecimalConvertor extends BigDecimalConverter {
    public Object fromString(String str) {
    	if(str!=null && "".equals(str.toString())){
    		BigDecimal e = BigDecimal.valueOf(-9999);
    		return e;
    	}
        return super.fromString(str);
    }
}
