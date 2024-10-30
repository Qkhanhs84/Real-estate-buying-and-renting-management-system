package com.javaweb.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class BuildingDTO extends AbstractDTO<BuildingDTO>{
    @NotBlank(message = "Building name is required")
    private String name;
    private String street;
    private String ward;
    @NotBlank(message = "District is required")
    private String district;
    private Long numberOfBasement;
    private Long floorArea;
    private String level;
    @Size(min = 1 , message = "Type code is required")
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
    @Min(value = 1,message = "Rent price must be greater than 0")
    @Max(value = 10000,message = "Rent price must be less than 10000")
    private Long rentPrice;
    private String serviceFee;
    private Double brokerageFee;
    private String image;
    private String imageBase64;
    private String imageName;

    private Map<String,String> buildingDTOs = new HashMap<>();


}