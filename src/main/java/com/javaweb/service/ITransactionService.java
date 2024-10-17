package com.javaweb.service;

import com.javaweb.entity.TransactionEntity;
import com.javaweb.model.dto.TransactionDTO;

import java.util.List;

public interface ITransactionService {
    List<TransactionDTO> findByCodeAndCustomerId(String code, Long customerId);
    void addOrUpdateTransaction(TransactionDTO transactionDTO);
    void deleteTransaction(Long id);
}
