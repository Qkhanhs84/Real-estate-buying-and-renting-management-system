package com.javaweb.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum rentType {
    TANG_TRET("Tầng trệt"),
    NGUYEN_CAN("Nguyên căn"),
    NOI_THAT("Nội thất") ;
    private final String name;  ;
    rentType(String name){
        this.name = name ;
    }
    public String getName(){
        return name ;
    }
    public static Map<String,String> getRentType(){
        Map<String,String> code = new LinkedHashMap<>();
        for(rentType rt : rentType.values()){
            code.put(rt.name(),rt.getName());
        }
        return code ;
    }
}
