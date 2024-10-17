package com.javaweb.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum status {
    CHUA_XU_LY("Chưa xử lý"),
    DANG_XU_LY("Đang xử lý"),
    DA_XU_LY("Đã xử lý") ;
    private final String name;  ;
    status(String name){
        this.name = name ;
    }
    public String getName(){
        return name ;
    }
    public static Map<String,String> getStatus(){
        Map<String,String> code = new LinkedHashMap<>();
        for(status st : status.values()){
            code.put(st.getName(),st.getName());
        }
        return code ;
    }
}
