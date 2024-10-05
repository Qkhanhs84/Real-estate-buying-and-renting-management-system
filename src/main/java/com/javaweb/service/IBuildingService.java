package com.javaweb.service;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;

import java.util.List;

public interface IBuildingService {
    List<BuildingSearchResponse> getBuildingList(BuildingSearchRequest buildingSearchRequest);
    BuildingDTO getBuildingById(Long id);
    void createOrUpdateBuilding(BuildingDTO buildingDTO);
    void deleteBuilding(List<Long> ids);
    void assignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO);
    int countTotalItem(BuildingSearchRequest buildingSearchRequest);
    boolean checkOwnerBuilding(Long id, Long userId);
}
