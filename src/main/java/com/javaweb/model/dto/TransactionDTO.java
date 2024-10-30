package com.javaweb.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class TransactionDTO extends AbstractDTO<TransactionDTO> {


    private String code;
    @NotBlank(message = "Note is required")
    private String note;

    private Long customerId;
    private Long staffId;




}
