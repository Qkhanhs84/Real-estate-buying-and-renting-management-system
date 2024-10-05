package com.javaweb.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "building")
public class BuildingEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(name = "name")
    private String name ;
    @Column(name = "street")
    private String street ;
    @Column(name = "ward")
    private String ward ;
    @Column(name = "district")
    private String district;
    @Column(name = "structure")
    private String structure;
    @Column(name = "numberofbasement")
    private Long numberOfBasement ;
    @Column(name = "floorarea")
    private Long floorArea ;
    @Column(name = "direction")
    private String direction ;
    @Column(name = "level")
    private String level  ;
    @Column(name = "rentprice")
    private Long rentPrice ;
    @Column(name = "rentpricedescription")
    private String rentDescription ;
    @Column(name = "servicefee")
    private Long serviceFee ;
    @Column(name = "carfee")
    private Long carFee ;
    @Column(name = "motofee")
    private Long motoFee;
    @Column(name = "overtimefee")
    private Long overtimeFee ;
    @Column(name = "waterfee")
    private Long waterFee;
    @Column(name = "electricityfee")
    private Long electricityFee;
    @Column(name = "deposit")
    private Long deposit;
    @Column(name = "payment")
    private Long payment ;
    @Column(name = "renttime")
    private String rentTime;
    @Column(name = "decorationtime")
    private String decorationTime ;
    @Column(name = "brokeragefee")
    private Double brokerageFee ;
    @Column(name = "note")
    private String note ;
    @Column(name = "managername")
    private String managerName;
    @Column(name = "managerphone")
    private String managerPhone;
    @Column(name = "type")
    private String typeCode;
    @Column(name = "avatar")
    private String image;

    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RentAreaEntity> rentAreas = new ArrayList<RentAreaEntity>();



    @ManyToMany(fetch = FetchType.LAZY )
    @JoinTable(name = "assignmentbuilding",
            joinColumns = @JoinColumn(name = "buildingid", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "staffid", nullable = false))
    private List<UserEntity> staffs = new ArrayList<>();
}
