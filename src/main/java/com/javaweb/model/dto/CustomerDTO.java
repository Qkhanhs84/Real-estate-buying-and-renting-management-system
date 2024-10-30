package com.javaweb.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CustomerDTO extends AbstractDTO{
    @NotBlank(message = "Full name is required")
    private String fullName;
    @Size(min = 10 , max = 11,message = "Phone number must be 10 or 11 digits")
    private String phone;
    private String email;
    private String demand;
    private String status;
    private String companyName;


}
