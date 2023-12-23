package com.wolfcodea.test.aplication.firstexample.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.wolfcodea.test.aplication.firstexample.errors.ErrorMessage;
import com.wolfcodea.test.aplication.firstexample.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex){
        ErrorMessage error = new ErrorMessage("Not Found", HttpStatus.NOT_FOUND.value(), ex.getMessage());
        
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
