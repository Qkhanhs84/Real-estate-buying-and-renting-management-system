package com.javaweb.service.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public List<CustomerEntity> getCustomerList(CustomerSearchRequest customerSearchRequest) {

        return customerRepository.getCustomerList(customerSearchRequest);
    }

    @Override
    public int countTotalItems(CustomerSearchRequest customerSearchRequest) {
        return customerRepository.countTotalItems(customerSearchRequest);
    }

    @Override
    public void createOrUpdateCustomer(CustomerEntity customerEntity) {
        customerEntity.setIsActive(1);
        if(customerEntity.getId()!=null){
            CustomerEntity oldCustomer = customerRepository.getOne(customerEntity.getId());
            customerEntity.setCreatedDate(oldCustomer.getCreatedDate());
            customerEntity.setCreatedBy(oldCustomer.getCreatedBy());
            customerEntity.setStaffsCustomer(oldCustomer.getStaffsCustomer());
            customerEntity.setTransactionEntities(oldCustomer.getTransactionEntities());

        }

        customerRepository.save(customerEntity);
    }

    @Override
    public CustomerEntity getCustomerById(Long id) {
        return customerRepository.getOne(id);
    }

    @Override
    public void assignCustomer(AssignmentDTO assignmentDTO) {
        CustomerEntity customerEntity = customerRepository.getOne(assignmentDTO.getId());
        if(assignmentDTO.getStaffs()!=null && assignmentDTO.getStaffs().size()>0) {
            customerEntity.setStaffsCustomer(userRepository.findByIdIn(assignmentDTO.getStaffs()));
        }
        else {
            customerEntity.setStaffsCustomer(null);
        }
        customerRepository.save(customerEntity);
    }

    @Override
    public void deleteCustomer(List<Long> ids) {
        if(ids!=null && !ids.isEmpty()){
            for(Long id:ids){
                CustomerEntity customerEntity = customerRepository.getOne(id);
                System.out.println(customerEntity.getId());
                customerEntity.setStaffsCustomer(null);
                customerEntity.getTransactionEntities().clear();
                customerEntity.setIsActive(0);
                customerRepository.save(customerEntity);
            }
        }
    }

    @Override
    public boolean checkOwnerCustomer(Long id, Long userId) {
        if(customerRepository.getOne(id) == null){
            return false;
        }
        List<UserEntity> staffs = customerRepository.getOne(id).getStaffsCustomer();
        if(staffs == null || staffs.isEmpty()){
            return false;
        }
        List<Long> staffIds = staffs.stream().map(user -> user.getId()).collect(Collectors.toList());
        return staffIds.contains(userId);
    }
}
