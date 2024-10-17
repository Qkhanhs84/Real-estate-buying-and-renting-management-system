package com.javaweb.service.impl;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.service.ICustomerService;
import com.javaweb.service.ITransactionService;
import com.javaweb.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService implements ITransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ModelMapper modelMapper ;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IUserService userService;
    @Override
    public List<TransactionDTO> findByCodeAndCustomerId(String code, Long customerId) {
        List<TransactionEntity> transactionEntityList = transactionRepository.findByCodeAndCustomerEntity_Id(code, customerId);
        List<TransactionDTO> result = transactionEntityList.stream().map(item -> modelMapper.map(item, TransactionDTO.class)).collect(Collectors.toList());
        return result;
    }

    @Override
    public void addOrUpdateTransaction(TransactionDTO transactionDTO) {
        TransactionEntity transactionEntity ;
        if(transactionDTO.getId() != null){
            TransactionEntity oldTransaction = transactionRepository.getOne(transactionDTO.getId());
            oldTransaction.setNote(transactionDTO.getNote());
            oldTransaction.setModifiedBy(transactionDTO.getModifiedBy());
            oldTransaction.setModifiedDate(transactionDTO.getModifiedDate());
            transactionEntity = oldTransaction;

        }
        else {
            transactionEntity = modelMapper.map(transactionDTO, TransactionEntity.class);
            CustomerEntity customerEntity = customerService.getCustomerById(transactionDTO.getCustomerId());
            transactionEntity.setCustomerEntity(customerEntity);
        }
        UserEntity userEntity = userService.findById(transactionDTO.getStaffId());
        transactionEntity.setUserEntity(userEntity);
        transactionRepository.save(transactionEntity);
    }

    @Override
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

}
