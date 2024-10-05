package com.javaweb.service.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.service.IRentAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RentAreaService implements IRentAreaService {
    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Override
    public List<RentAreaEntity> addRentArea(List<String> rentArea , BuildingEntity buildingEntity) {
        List<RentAreaEntity> rentAreaEntityList = new ArrayList<>();
        for(String ra : rentArea){
            RentAreaEntity rentAreaEntity = new RentAreaEntity();
            rentAreaEntity.setValue(Long.parseLong(ra));
            rentAreaEntity.setBuilding(buildingEntity);
            rentAreaEntityList.add(rentAreaEntity);
        }
        return rentAreaEntityList ;
    }
}
