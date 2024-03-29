package com.example.onlinevotingsystem.controllers;

import com.example.onlinevotingsystem.exceptions.AlreadyApplyApplicationException;
import com.example.onlinevotingsystem.exceptions.InvalidApplicationException;
import com.example.onlinevotingsystem.models.apiModels.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({AlreadyApplyApplicationException.class})
    public ResponseEntity<Object> alreadyApplyApplicationExceptionHandler(AlreadyApplyApplicationException ex,WebRequest webRequest) {

        return  new ResponseEntity<>(new ApiError(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,LocalDateTime.now()),HttpStatus.INTERNAL_SERVER_ERROR);
    }
    // This is not working
    @ExceptionHandler({InvalidApplicationException.class})
    public ResponseEntity<Object> invalidApplicationExceptionHandler(InvalidApplicationException ex,WebRequest webRequest) {
        return  new ResponseEntity<>(new ApiError(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,LocalDateTime.now()),HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
