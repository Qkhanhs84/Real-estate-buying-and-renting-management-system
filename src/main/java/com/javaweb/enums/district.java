package com.javaweb.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum district {
    QUAN_1("Quận 1"),
    QUAN_2("Quận 2"),
    QUAN_3("Quận 3"),
    QUAN_4("Quận 4"),
    QUAN_5("Quận 5"),
    QUAN_6("Quận 6"),
    QUAN_7("Quận 7"),
    QUAN_8("Quận 8"),
    QUAN_9("Quận 9"),
    QUAN_10("Quận 10"),
    QUAN_11("Quận 11"),
    QUAN_12("Quận 12"),
    QUẠN_TK("Quận Thanh Khê") ;
    private final String districtName ;
    district(String name){
        this.districtName = name ;
    }
    public String getDistrictName(){
        return districtName ;
    }
    public static Map<String,String> getDistrict(){
        Map<String,String> code = new LinkedHashMap<>();
        for(district d : district.values()){
            code.put(d.name(),d.getDistrictName());
        }
        return code ;
    }
}
