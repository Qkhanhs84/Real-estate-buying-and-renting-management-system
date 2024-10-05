package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.BuildingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.javaweb.enums.district;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BuildingConverter {
    @Autowired
    private ModelMapper modelMapper ;

    @Autowired
    private BuildingRepository buildingRepository;

    public BuildingSearchResponse buildingEntityToBuildingSearchResponse(BuildingEntity buildingEntity){
        BuildingSearchResponse buildingSearchResponse = modelMapper.map(buildingEntity, BuildingSearchResponse.class) ;
        String districtName = ""  ;
        if(buildingEntity.getDistrict()!=null && !buildingEntity.getDistrict().isEmpty()){
            districtName = district.valueOf(buildingEntity.getDistrict()).getDistrictName();
        }
        buildingSearchResponse.setAddress(buildingEntity.getStreet() + ", " + buildingEntity.getWard() + ", " + districtName) ;
        if(buildingEntity.getRentAreas()!=null && !buildingEntity.getRentAreas().isEmpty()){
            buildingSearchResponse.setRentArea(buildingEntity.getRentAreas().stream().map(item -> item.getValue().toString()).collect(Collectors.joining(",")));

        }
        return buildingSearchResponse;
    }
    public BuildingEntity buildingDTOToBuildingEntity(BuildingDTO buildingDTO){
        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
        buildingEntity.setTypeCode(buildingDTO.getTypeCode().stream().collect(Collectors.joining(",")));
        return buildingEntity;
    }
    public BuildingDTO buildingEntityToBuildingDTO(BuildingEntity buildingEntity){
        BuildingDTO buildingDTO = modelMapper.map(buildingEntity, BuildingDTO.class);
        buildingDTO.setTypeCode(Arrays.asList(buildingEntity.getTypeCode().split(",")));
        buildingDTO.setRentArea(buildingEntity.getRentAreas().stream().map(item -> item.getValue().toString()).collect(Collectors.joining(",")));
        return buildingDTO;
    }
}
