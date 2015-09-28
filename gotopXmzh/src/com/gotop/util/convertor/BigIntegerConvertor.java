package com.gotop.util.convertor;

import java.math.BigInteger;

import com.thoughtworks.xstream.converters.basic.BigIntegerConverter;

public class BigIntegerConvertor extends BigIntegerConverter {
    public Object fromString(String str) {
    	if(str!=null && "".equals(str.toString())){
    		BigInteger e = BigInteger.valueOf(-9999);
    		return e;
    	}
        return super.fromString(str);
    }
}
