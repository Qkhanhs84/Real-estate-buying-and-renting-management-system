package com.javaweb.service;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.dto.AssignmentDTO;
import com.javaweb.model.request.CustomerSearchRequest;

import java.util.List;

public interface ICustomerService {
    List<CustomerEntity> getCustomerList(CustomerSearchRequest customerSearchRequest);
    int countTotalItems(CustomerSearchRequest customerSearchRequest);
    void createOrUpdateCustomer(CustomerEntity customerEntity);
    CustomerEntity getCustomerById(Long id);
    void assignCustomer(AssignmentDTO assignmentDTO);
    void deleteCustomer(List<Long> ids);
    boolean checkOwnerCustomer(Long id, Long userId);
}
