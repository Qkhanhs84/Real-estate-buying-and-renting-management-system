package com.javaweb.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class CustomerEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "demand")
    private String demand;

    @Column(name = "status")
    private String status;

    @Column(name = "companyname")
    private String companyName;

    @Column(name = "is_active")
    private Integer isActive;

    @OneToMany(mappedBy = "customerEntity" , fetch = FetchType.LAZY , cascade = CascadeType.ALL, orphanRemoval = true)
    List<TransactionEntity> transactionEntities = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY )
    @JoinTable(name = "assignmentcustomer",
            joinColumns = @JoinColumn(name = "customerid", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "staffid", nullable = false))
    private List<UserEntity> staffsCustomer = new ArrayList<>();

}
