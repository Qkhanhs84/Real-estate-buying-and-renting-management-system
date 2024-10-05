package com.javaweb.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class BuildingDTO extends AbstractDTO<BuildingDTO>{

    private String name;
    private String street;
    private String ward;
    private String district;
    private Long numberOfBasement;
    private Long floorArea;
    private String level;
    private List<String> typeCode;
    private String overtimeFee;
    private String electricityFee;
    private String waterFee;
    private String deposit;
    private String payment;
    private String rentTime;
    private String decorationTime;
    private String rentDescription;
    private String carFee;
    private String motoFee;
    private String structure;
    private String direction;
    private String note;
    private String rentArea;
    private String managerName;
    private String managerPhone;
    private Long rentPrice;
    private String serviceFee;
    private Double brokerageFee;
    private String image;
    private String imageBase64;
    private String imageName;

    private Map<String,String> buildingDTOs = new HashMap<>();


}