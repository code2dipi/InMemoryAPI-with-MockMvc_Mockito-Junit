package com.dipi.inmemory_api_mock_mvc_test.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Handle the specific exception
    @ExceptionHandler(CustomClassException.class)
    public ResponseEntity<?> handleCustomTypeOfException(CustomClassException ce, WebRequest request){
      // Log the exception
        logger.error("Resource Not Found exception: {}",ce.getMessage());
        ErrorDetails errorDetails=new ErrorDetails(new Date().toString(),ce.getMessage(),request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);

    }

    //Handle Global Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGloableException(Exception ex,WebRequest request){
        // Log exception with the stack trace
        logger.error("An error occurred: ",ex);
        ErrorDetails errorDetails=new ErrorDetails( new Date().toString(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
