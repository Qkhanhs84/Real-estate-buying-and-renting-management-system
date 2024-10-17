package com.javaweb.repository.custom.impl;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.repository.custom.CustomerRepositoryCustom;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.crypto.Cipher;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    public StringBuilder getQuery(CustomerSearchRequest customerSearchRequest){
        StringBuilder sql = new StringBuilder("SELECT distinct c.* FROM customer c");
        StringBuilder where = new StringBuilder(" WHERE is_active = 1 ");
        if (customerSearchRequest.getStaffId() != null) {
            sql.append(" join assignmentcustomer ac on c.id = ac.customerid");
            where.append(" and ac.staffid = " + customerSearchRequest.getStaffId());

        }
        Field[] fields = CustomerSearchRequest.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            if (fieldName.equals("staffId")) {
                continue;
            }
            try {
                Object value = field.get(customerSearchRequest);
                if (value != null && !value.toString().equals("")) {

                    where.append(" AND c." + fieldName + " LIKE '%" + value + "%'");

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        sql.append(where);
        return sql;
    }
    @Override
    public List<CustomerEntity> getCustomerList(CustomerSearchRequest customerSearchRequest) {

        StringBuilder sql = getQuery(customerSearchRequest);

        Pageable pageable = (Pageable) PageRequest.of(customerSearchRequest.getPage() - 1, customerSearchRequest.getMaxPageItems());
        sql.append(" LIMIT ").append(pageable.getPageSize()) ;
        sql.append(" OFFSET ").append(pageable.getOffset()) ;
        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);
        return query.getResultList();

    }

    @Override
    public int countTotalItems(CustomerSearchRequest customerSearchRequest) {
        StringBuilder sql = getQuery(customerSearchRequest);
        Query query = entityManager.createNativeQuery(sql.toString());
        return query.getResultList().size();
    }

}
