package com.javaweb.api.admin;

import com.javaweb.converter.BuildingConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/buildings")
@Transactional
public class BuildingAPI {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IBuildingService buildingService;
    @Autowired
    private UserService userService;

    @PostMapping
    public void createOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO){
        buildingService.createOrUpdateBuilding(buildingDTO);

    }

    @GetMapping("/{buildingId}/staffs")
    public ResponseDTO getAllStaffs(@PathVariable Long buildingId){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Get all staffs success");
        responseDTO.setData(userService.getAllStaffs(buildingId));
        return responseDTO;
    }

    @DeleteMapping("/{ids}")
    public void deleteBuilding(@PathVariable() List<Long> ids){
        buildingService.deleteBuilding(ids);
    }

    @PostMapping("/staffs")
    public void updateAssignmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO){
        buildingService.assignmentBuilding(assignmentBuildingDTO);
    }
}
