package com.learning.SlotBookingApplication.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
 
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleNotFoundException(NotFoundException notFoundException){
        return new ResponseEntity<>(new ExceptionDTO(HttpStatus.NOT_FOUND, notFoundException.getMessage()),
         HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ExceptionDTO> handleAlreadyExistsException(AlreadyExistsException alreadyExistsException){
        return new ResponseEntity<>(new ExceptionDTO(HttpStatus.NOT_FOUND, alreadyExistsException.getMessage()),
        HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidCredentialException.class)
    public ResponseEntity<ExceptionDTO> handleInvalidCredentialException(InvalidCredentialException exception){
        return new ResponseEntity<>(new ExceptionDTO(HttpStatus.NOT_FOUND, exception.getMessage()),
        HttpStatus.NOT_FOUND);
    }
}
