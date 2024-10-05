package com.javaweb.repository.custom.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager   ;

    private void queryJoin(BuildingSearchRequest buildingSearchRequest, StringBuilder join){
        Long staffId = buildingSearchRequest.getStaffId();
        if(staffId != null){
            join.append(" JOIN assignmentbuilding ab on b.id = ab.buildingid ");
        }

    }
    private void queryNormal(BuildingSearchRequest buildingSearchRequest, StringBuilder where){
        Field[] fields = BuildingSearchRequest.class.getDeclaredFields();
        for(Field field :fields){
            field.setAccessible(true);
            String fieldName = field.getName();
            if(fieldName.equals("staffId") || fieldName.equals("typeCode") || fieldName.startsWith("area") || fieldName.startsWith("rentPrice")){
                continue;
            }
            try {
                Object value = field.get(buildingSearchRequest);
                if(value != null && !value.toString().equals("")){
                   if(field.getType().getName().equals("java.lang.String")){
                       where.append(" AND b."+fieldName + " LIKE '%" + value + "%'");
                   }
                   else{
                       where.append(" AND b."+fieldName + " = " + value);
                   }
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }

    }
    private void querySpecial(BuildingSearchRequest buildingSearchRequest, StringBuilder where){
        Long areaTo = buildingSearchRequest.getAreaTo();
        Long areaFrom = buildingSearchRequest.getAreaFrom();
        if(areaFrom!= null || areaTo != null){
            where.append(" AND EXISTS (SELECT * FROM rentArea ra WHERE b.id = ra.buildingid ") ;
            if(areaFrom != null){
                where.append(" AND ra.value >= "+areaFrom);
            }
            if(areaTo != null){
                where.append(" AND ra.value <= "+areaTo);
            }
            where.append(" )");
        }
        Long staffId = buildingSearchRequest.getStaffId();
        if(staffId != null){
            where.append(" AND ab.staffid = "+staffId);
        }
        Long rentPriceFrom = buildingSearchRequest.getRentPriceFrom();
        Long rentPriceTo = buildingSearchRequest.getRentPriceTo();
        if(rentPriceTo!=null){
            where.append(" AND b.rentPrice <= "+rentPriceTo);
        }
        if(rentPriceFrom!=null){
            where.append(" AND b.rentPrice >= "+rentPriceFrom);
        }
        List<String> typeCode = buildingSearchRequest.getTypeCode();
        if(typeCode !=null && !typeCode.isEmpty()){
            where.append(" AND (");
            where.append(typeCode.stream().map(type -> " b.type LIKE '%"+type+"%'").collect(Collectors.joining(" OR ")));
            where.append(")");

        }


    }

    @Override
    public List<BuildingEntity> getBuildingList(BuildingSearchRequest buildingSearchRequest) {
        List<BuildingEntity> result =new ArrayList<>() ;
        StringBuilder sql = new StringBuilder("SELECT distinct b.* FROM building b ") ;
        StringBuilder where = new StringBuilder(" WHERE 1=1 ") ;
        queryJoin(buildingSearchRequest,sql);
        queryNormal(buildingSearchRequest,where);
        querySpecial(buildingSearchRequest,where);
        sql.append(where);
        Pageable pageable = (Pageable) PageRequest.of(buildingSearchRequest.getPage()-1,buildingSearchRequest.getMaxPageItems());
        sql.append(" LIMIT ").append(pageable.getPageSize()) ;
        sql.append(" OFFSET ").append(pageable.getOffset()) ;

        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList() ;
    }

    @Override
    public int countTotalItem(BuildingSearchRequest buildingSearchRequest) {

        StringBuilder sql = new StringBuilder("SELECT count(b.id) FROM building b ") ;
        StringBuilder where = new StringBuilder(" WHERE 1=1 ") ;
        queryJoin(buildingSearchRequest,sql);
        queryNormal(buildingSearchRequest,where);
        querySpecial(buildingSearchRequest,where);
        sql.append(where);
        Query query = entityManager.createNativeQuery(sql.toString());
        return Integer.parseInt(query.getSingleResult().toString());

    }
}
