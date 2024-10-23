package com.javaweb.api.admin;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.exception.DataNotFoundExecption;
import com.javaweb.model.dto.AssignmentDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.ICustomerService;
import com.javaweb.service.ITransactionService;
import com.javaweb.service.IUserService;
import com.javaweb.service.impl.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
@RequestMapping("/api/customers")
public class CustomerAPI {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private IUserService userService;


    @PostMapping
    public ResponseEntity<?> createOrUpdateCustomer(@Valid @RequestBody CustomerDTO customerDTO, BindingResult bindingResult) {
        try {
            ResponseDTO responseDTO = new ResponseDTO();
            if (bindingResult.hasErrors()) {
                responseDTO = new ResponseDTO();
                responseDTO.setMessage("Error");
                List<String> errors = bindingResult.getFieldErrors().stream().
                        map(FieldError::getDefaultMessage).collect(Collectors.toList());
                responseDTO.setDetail(errors);
                throw new DataNotFoundExecption("Error");

            }
            customerService.createOrUpdateCustomer(customerDTO);
            responseDTO.setMessage("Success");
            return ResponseEntity.ok(responseDTO);

        } catch (Exception e) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage("Error");
            responseDTO.setDetail(Stream.of(e.getMessage()).collect(Collectors.toList()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
    }

    @GetMapping("/{id}/staffs")
    public ResponseDTO getStaffsByCustomerId(@PathVariable Long id) {
        if (id == null) {
            throw new DataNotFoundExecption("Id is required");
        }

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Get all staffs success");

        responseDTO.setData(userService.getAllCustomerStaffs(id));
        return responseDTO;
    }

    @PostMapping("/staffs")
    public ResponseEntity<?> assignCustomer(@RequestBody AssignmentDTO assignmentDTO) {

        customerService.assignCustomer(assignmentDTO);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Success");
        return ResponseEntity.ok(responseDTO);

    }

    @DeleteMapping("/{ids}")
    public ResponseEntity<?> deleteCustomer(@PathVariable List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new DataNotFoundExecption("Id is required");
        }

        customerService.deleteCustomer(ids);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Success");
        return ResponseEntity.ok(responseDTO);

    }

}
