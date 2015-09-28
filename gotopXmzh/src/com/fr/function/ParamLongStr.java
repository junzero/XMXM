package com.fr.function;

import java.util.HashMap;
import java.util.Map;

import com.fr.script.AbstractFunction;

public class ParamLongStr extends AbstractFunction{
    private static Map<String,String> mess = new HashMap<String,String>();
    
    @Override
    public Object run(Object[] arg0) {
        if(arg0.length<1 || arg0[0]==null){
            return "";
        }
        String key = String.valueOf(arg0[0]);
        String value = mess.get(key);
        mess.remove(key);
        return value;
    }
    public static void setKeyValue(String key,String value){
        mess.put(key, value);
    }
    
}
