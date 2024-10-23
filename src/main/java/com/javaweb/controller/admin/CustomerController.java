package com.javaweb.controller.admin;


import com.javaweb.entity.CustomerEntity;
import com.javaweb.enums.status;
import com.javaweb.enums.transaction;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.ICustomerService;
import com.javaweb.service.ITransactionService;
import com.javaweb.service.IUserService;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController()

public class CustomerController {
    @Autowired
    private IUserService userService;
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ITransactionService transactionService;

    @GetMapping("/admin/customer-list")
    public ModelAndView getCustomerList(@ModelAttribute(name = "modelSearch") CustomerSearchRequest customerSearchRequest , HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/customer/list");
        DisplayTagUtils.of(request,customerSearchRequest,"customerList");
        modelAndView.addObject("status", status.getStatus());
        modelAndView.addObject("staffs", userService.getStaffs()) ;
        if(SecurityUtils.getAuthorities().contains("ROLE_STAFF")){
            customerSearchRequest.setStaffId(SecurityUtils.getPrincipal().getId());
        }
        modelAndView.addObject("customers", customerService.getCustomerList(customerSearchRequest));
        customerSearchRequest.setTotalItems(customerService.countTotalItems(customerSearchRequest));
        return modelAndView;
    }
    @GetMapping("/admin/customer-edit")
    public ModelAndView customerEdit(@ModelAttribute(name = "customer") CustomerDTO customerDTO){
        ModelAndView modelAndView = new ModelAndView("admin/customer/edit");
        modelAndView.addObject("status", status.getStatus());
        return modelAndView;
    }
    @GetMapping("/admin/customer-edit-{id}")
    public ModelAndView customerEdit(@PathVariable Long id){
        if(!SecurityUtils.getAuthorities().contains("ROLE_MANAGER")){
            if(!customerService.checkOwnerCustomer(id,SecurityUtils.getPrincipal().getId())){
                return new ModelAndView("redirect:/access-denied");
            }
        }
        ModelAndView modelAndView = new ModelAndView("admin/customer/edit");
        modelAndView.addObject("status", status.getStatus());
        CustomerDTO customerDTO = customerService.getCustomerDTOById(id);
        modelAndView.addObject("customer", customerDTO);
        modelAndView.addObject("transactions", transaction.getTransaction());
        modelAndView.addObject("CSKH",transactionService.findByCodeAndCustomerId("CSKH",id));
        modelAndView.addObject("DDX",transactionService.findByCodeAndCustomerId("DDX",id));
        return modelAndView;
    }
}
