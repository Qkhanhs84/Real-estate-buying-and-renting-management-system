package com.javaweb.api.admin;

import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionAPI {
    @Autowired
    private ITransactionService transactionService;

    @PostMapping
    private void addOrUpdateTransaction(@RequestBody TransactionDTO transactionDTO){
        Long staffId = SecurityUtils.getPrincipal().getId();
        transactionDTO.setStaffId(staffId);
        transactionService.addOrUpdateTransaction(transactionDTO);

    }
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id){
        transactionService.deleteTransaction(id);
    }
}
