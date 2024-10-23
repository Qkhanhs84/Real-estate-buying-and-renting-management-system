package com.javaweb.service.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.exception.DataNotFoundExecption;
import com.javaweb.model.dto.AssignmentDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.ICustomerService;
import org.modelmapper.ModelMapper;
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
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CustomerDTO> getCustomerList(CustomerSearchRequest customerSearchRequest) {
        List<CustomerDTO> result = new ArrayList<>();
        List<CustomerEntity> customerEntities = customerRepository.getCustomerList(customerSearchRequest);
        result = customerEntities.stream().map(customerEntity -> modelMapper.map(customerEntity, CustomerDTO.class)).
                collect(Collectors.toList());
        return result;
    }

    @Override
    public int countTotalItems(CustomerSearchRequest customerSearchRequest) {
        return customerRepository.countTotalItems(customerSearchRequest);
    }

    @Override
    public void createOrUpdateCustomer( CustomerDTO customerDTO) {
        CustomerEntity customerEntity = modelMapper.map(customerDTO, CustomerEntity.class);
        customerEntity.setIsActive(1);
        if(customerEntity.getId()!=null){
            CustomerEntity oldCustomer = customerRepository.findById(customerEntity.getId()).orElseThrow(()->
                    new DataNotFoundExecption("Customer not found"));
            customerEntity.setCreatedDate(oldCustomer.getCreatedDate());
            customerEntity.setCreatedBy(oldCustomer.getCreatedBy());
            customerEntity.setStaffsCustomer(oldCustomer.getStaffsCustomer());
            customerEntity.setTransactionEntities(oldCustomer.getTransactionEntities());

        }

        customerRepository.save(customerEntity);
    }

    @Override
    public CustomerDTO getCustomerDTOById(Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow(()->
                new DataNotFoundExecption("Customer not found"));
        return modelMapper.map(customerEntity, CustomerDTO.class);

    }

    @Override
    public CustomerEntity getCustomerEntityById(Long id) {
        return customerRepository.findById(id).orElseThrow(()->
                new DataNotFoundExecption("Customer not found"));
    }

    @Override
    public void assignCustomer(AssignmentDTO assignmentDTO) {
        CustomerEntity customerEntity = customerRepository.findById(assignmentDTO.getId()).orElseThrow(
                () -> new DataNotFoundExecption("Customer not found"));
        if(assignmentDTO.getStaffs()!=null && !assignmentDTO.getStaffs().isEmpty()) {
            customerEntity.setStaffsCustomer(userRepository.findByIdIn(assignmentDTO.getStaffs()));
        }
        else {
            customerEntity.setStaffsCustomer(null);
        }
        customerRepository.save(customerEntity);
    }

    @Override
    public void deleteCustomer(List<Long> ids) {


        List<CustomerEntity> customerEntities = customerRepository.findByIdIn(ids) ;
        if(customerEntities == null || customerEntities.isEmpty()){
            throw new DataNotFoundExecption("Customer not found");
        }

        List<CustomerEntity> result = customerEntities.stream().map(customerEntity -> {
            customerEntity.setIsActive(0);
            return customerEntity;
        }).collect(Collectors.toList());
        customerRepository.saveAll(result);
    }

    @Override
    public boolean checkOwnerCustomer(Long id, Long userId) {
        CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow(()->
                new DataNotFoundExecption("Customer not found"));
        List<UserEntity> staffs = customerEntity.getStaffsCustomer();
        if(staffs == null || staffs.isEmpty()){
            return false;
        }
        List<Long> staffIds = staffs.stream().map(user -> user.getId()).collect(Collectors.toList());
        return staffIds.contains(userId);
    }
}
