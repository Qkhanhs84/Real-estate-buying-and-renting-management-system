package com.javaweb.api.admin;

import com.javaweb.converter.BuildingConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;

import com.javaweb.model.dto.AssignmentDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public ResponseEntity<?> createOrUpdateBuilding(@Valid @RequestBody BuildingDTO buildingDTO, BindingResult bindingResult){
        try {
            ResponseDTO responseDTO = new ResponseDTO();
            if(bindingResult.hasErrors()){
                responseDTO.setMessage("Error");
                List<String> errors = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
                responseDTO.setDetail(errors);
                return ResponseEntity.badRequest().body(responseDTO);
            }
            buildingService.createOrUpdateBuilding(buildingDTO);
            responseDTO.setMessage("Success");
            return ResponseEntity.ok(responseDTO);
        }catch (Exception e){
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage("Error");
            responseDTO.setDetail(Stream.of(e.getMessage()).collect(Collectors.toList()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }

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
    public void updateAssignmentBuilding(@RequestBody AssignmentDTO assignmentDTO){
        buildingService.assignmentBuilding(assignmentDTO);
    }
}
