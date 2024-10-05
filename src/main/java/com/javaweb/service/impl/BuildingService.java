package com.javaweb.service.impl;

import com.javaweb.converter.BuildingConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.IRentAreaService;
import com.javaweb.utils.UploadFileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuildingService implements IBuildingService {
    @Autowired
    private BuildingConverter buildingConverter;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private RentAreaRepository rentAreaRepository;
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private UploadFileUtils uploadFileUtils;

    @Autowired
    private IRentAreaService rentAreaService;

    @Override
    public List<BuildingSearchResponse> getBuildingList(BuildingSearchRequest buildingSearchRequest) {

        List<BuildingEntity> buildingEntityList = buildingRepository.getBuildingList(buildingSearchRequest);
        List<BuildingSearchResponse> result = new ArrayList<>();
        for (BuildingEntity buildingEntity : buildingEntityList) {
            result.add(buildingConverter.buildingEntityToBuildingSearchResponse(buildingEntity));
        }
        return result;
    }

    @Override
    public BuildingDTO getBuildingById(Long id) {
        BuildingEntity buildingEntity = buildingRepository.getOne(id);
        return buildingConverter.buildingEntityToBuildingDTO(buildingEntity);
    }

    @Override
    public void createOrUpdateBuilding(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = buildingConverter.buildingDTOToBuildingEntity(buildingDTO);

        if(buildingDTO.getRentArea()!= null && !buildingDTO.getRentArea().equals("")){
            List<String> rentArea = Arrays.asList(buildingDTO.getRentArea().split(","));
            buildingEntity.setRentAreas(rentAreaService.addRentArea(rentArea,buildingEntity));
        }
        if(buildingDTO.getId()!=null){
            BuildingEntity oldBuilding = buildingRepository.getOne(buildingDTO.getId());
            buildingEntity.setStaffs(oldBuilding.getStaffs());
            buildingEntity.setImage(oldBuilding.getImage());
        }
        saveThumbnail(buildingDTO,buildingEntity);
        buildingRepository.save(buildingEntity);


    }
    public void saveThumbnail(BuildingDTO buildingDTO , BuildingEntity buildingEntity){
        if(buildingDTO.getImageBase64()!=null && !buildingDTO.getImageBase64().equals("")){
            String path = "/building/"+buildingDTO.getImageName();
            if(buildingEntity.getImage()!=null && !buildingEntity.getImage().equals("")){
                if(path.equals(buildingEntity.getImage())){
                    File file = new File("D://BE/image"+buildingEntity.getImage());
                    file.delete();
                }

            }
            byte[] bytes = Base64.decodeBase64(buildingDTO.getImageBase64().split(",")[1]);
            uploadFileUtils.writeOrUpdate(path,bytes);
            buildingEntity.setImage(path);
        }
    }

    @Override
    public void deleteBuilding(List<Long> ids) {

        if(ids!=null && ids.size()>0){
            buildingRepository.deleteByIdIn(ids);
        }



    }

    @Override
    public void assignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO) {
        BuildingEntity buildingEntity = buildingRepository.getOne(assignmentBuildingDTO.getBuildingId());
        if(assignmentBuildingDTO.getStaffs()!=null && assignmentBuildingDTO.getStaffs().size()>0) {
            buildingEntity.setStaffs(userRepository.findByIdIn(assignmentBuildingDTO.getStaffs()));
        }
        else {
            buildingEntity.setStaffs(null);
        }
    }

    @Override
    public int countTotalItem(BuildingSearchRequest buildingSearchRequest) {
        return buildingRepository.countTotalItem(buildingSearchRequest);
    }

    @Override
    public boolean checkOwnerBuilding(Long id, Long userId) {
        if(buildingRepository.getOne(id) == null){
            return false;
        }
        List<UserEntity> staffs = buildingRepository.getOne(id).getStaffs();
        if(staffs == null || staffs.size() == 0){
            return false;
        }
        List<Long> staffIds = staffs.stream().map(user -> user.getId()).collect(Collectors.toList());
        return staffIds.contains(userId);
    }
}
