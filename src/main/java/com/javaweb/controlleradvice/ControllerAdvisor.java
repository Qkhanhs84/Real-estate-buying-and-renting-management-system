package com.javaweb.controlleradvice;

import com.javaweb.exception.DataNotFoundExecption;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor  {
    @ExceptionHandler(DataNotFoundExecption.class)
    public ResponseEntity<?> handleDataNotFoundExecption(DataNotFoundExecption ex){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(ex.getMessage());

        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
    }
}
