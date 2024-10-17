package com.javaweb.api.admin;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.AssignmentDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.ICustomerService;
import com.javaweb.service.ITransactionService;
import com.javaweb.service.IUserService;
import com.javaweb.service.impl.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerAPI {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private IUserService userService;


    @PostMapping
    public void createOrUpdateCustomer(@RequestBody CustomerEntity customerEntity){
        customerService.createOrUpdateCustomer(customerEntity);
    }
    @GetMapping("/{id}/staffs")
    public ResponseDTO getStaffsByCustomerId(@PathVariable Long id){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Get all staffs success");
        responseDTO.setData(userService.getAllCustomerStaffs(id));
        return responseDTO;
    }
    @PostMapping("/staffs")
    public void assignCustomer(@RequestBody AssignmentDTO assignmentDTO){
        customerService.assignCustomer(assignmentDTO);
    }

    @DeleteMapping("/{ids}")
    public void deleteCustomer(@PathVariable List<Long> ids){
        customerService.deleteCustomer(ids);
    }

}
