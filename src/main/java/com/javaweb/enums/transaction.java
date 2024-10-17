package com.javaweb.enums;

import java.util.Map;

public enum transaction {
    DDX("Dẫn đi xem") ,
    CSKH("Chăm sóc khách hàng") ;
    private final String name ;
    transaction(String name){
        this.name = name ;
    }
    public String getName(){
        return name ;
    }
    public static Map<String, String> getTransaction(){
        Map<String, String> code = new java.util.LinkedHashMap<>();
        for(transaction tr : transaction.values()){
            code.put(tr.name(), tr.getName());
        }
        return code ;
    }
}
