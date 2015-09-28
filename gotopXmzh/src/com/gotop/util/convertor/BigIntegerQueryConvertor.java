package com.gotop.util.convertor;

import java.math.BigInteger;

import com.thoughtworks.xstream.converters.basic.BigIntegerConverter;

public class BigIntegerQueryConvertor extends BigIntegerConverter {
    public Object fromString(String str) {
    	if(str!=null && "".equals(str.toString())){
    		return null;
    	}
        return super.fromString(str);
    }
}
