package com.javaweb.controller.admin;



import com.javaweb.enums.district;
import com.javaweb.enums.rentType;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.IUserService;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController(value="buildingControllerOfAdmin")
public class BuildingController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IBuildingService buildingService;


    @GetMapping("/admin/building-list")
    public ModelAndView getBuildingList(@ModelAttribute(name = "modelSearch") BuildingSearchRequest buildingSearchRequest, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/building/list");
        DisplayTagUtils.of(request,buildingSearchRequest,"buildingList");
        modelAndView.addObject("districtCode" , district.getDistrict());
        modelAndView.addObject("staffs", userService.getStaffs()) ;
        modelAndView.addObject("typeCode" , rentType.getRentType());
        if(SecurityUtils.getAuthorities().contains("ROLE_STAFF")){
            buildingSearchRequest.setStaffId(SecurityUtils.getPrincipal().getId());
        }
        int totalItems = buildingService.countTotalItem(buildingSearchRequest);
        buildingSearchRequest.setTotalItems(totalItems);
        modelAndView.addObject("buildingList",buildingService.getBuildingList(buildingSearchRequest));
        return modelAndView ;
    }
    @GetMapping("/admin/building-edit")
    public ModelAndView buildingEdit(@ModelAttribute(name = "building") BuildingDTO buildingDTO){
        ModelAndView modelAndView = new ModelAndView("admin/building/edit");
        modelAndView.addObject("districtCode" , district.getDistrict());
        modelAndView.addObject("typeCode" , rentType.getRentType());
        return modelAndView ;
    }
    @GetMapping("/admin/building-edit-{id}")
    public ModelAndView buildingEdit(@PathVariable Long id){
        if(!SecurityUtils.getAuthorities().contains("ROLE_MANAGER")){
            if(!buildingService.checkOwnerBuilding(id,SecurityUtils.getPrincipal().getId())){
                return new ModelAndView("redirect:/access-denied");
            }
        }
        ModelAndView modelAndView = new ModelAndView("admin/building/edit");
        modelAndView.addObject("districtCode" , district.getDistrict());
        modelAndView.addObject("typeCode" , rentType.getRentType());

        modelAndView.addObject("building",buildingService.getBuildingById(id));
        return modelAndView ;
    }


}
