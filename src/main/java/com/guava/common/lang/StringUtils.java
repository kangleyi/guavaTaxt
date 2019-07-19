package com.guava.common.lang;

public class StringUtils {

    public static String[] split(String r,String f){
        if(null!=r && !r.isEmpty()){
            return r.split(f);
        }
        return null;
    }
}
