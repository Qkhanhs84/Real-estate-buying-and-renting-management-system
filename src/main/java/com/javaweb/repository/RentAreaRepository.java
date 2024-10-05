package com.javaweb.repository;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface RentAreaRepository extends JpaRepository<RentAreaEntity, Long> {
    void deleteByBuildingIn(List<BuildingEntity> dbs);
    void deleteAllByBuilding(BuildingEntity buildingEntity);
}
