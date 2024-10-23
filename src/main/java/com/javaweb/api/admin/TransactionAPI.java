package com.javaweb.api.admin;

import com.javaweb.exception.DataNotFoundExecption;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/transactions")
public class TransactionAPI {
    @Autowired
    private ITransactionService transactionService;

    @PostMapping
    private ResponseEntity<?> addOrUpdateTransaction(@Valid @RequestBody TransactionDTO transactionDTO , BindingResult bindingResult) {
        try {
            ResponseDTO responseDTO = new ResponseDTO();

            if (bindingResult.hasErrors()) {
                responseDTO.setMessage("Error");
                List<String> errors = bindingResult.getFieldErrors().stream().
                        map(FieldError::getDefaultMessage).collect(Collectors.toList());
                responseDTO.setDetail(errors);
                return ResponseEntity.badRequest().body(responseDTO);
            }

            Long staffId = SecurityUtils.getPrincipal().getId();
            transactionDTO.setStaffId(staffId);
            transactionService.addOrUpdateTransaction(transactionDTO);
            responseDTO.setMessage("Success");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage("Error");
            responseDTO.setDetail(Stream.of(e.getMessage()).collect(Collectors.toList()));
            return ResponseEntity.badRequest().body(responseDTO);

        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id){
        if(id==null){
            throw new  DataNotFoundExecption("Id is null");
        }

        transactionService.deleteTransaction(id);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Success");
        return ResponseEntity.ok(responseDTO);
    }
}
