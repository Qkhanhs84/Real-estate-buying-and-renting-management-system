package com.javaweb.service;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.dto.AssignmentDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;


import java.util.List;

public interface ICustomerService {
    List<CustomerDTO> getCustomerList(CustomerSearchRequest customerSearchRequest);
    int countTotalItems(CustomerSearchRequest customerSearchRequest);
    void createOrUpdateCustomer(CustomerDTO customerDTO);
    CustomerDTO getCustomerDTOById(Long id);
    CustomerEntity getCustomerEntityById(Long id);
    void assignCustomer(AssignmentDTO assignmentDTO);
    void deleteCustomer(List<Long> ids);
    boolean checkOwnerCustomer(Long id, Long userId);
}
