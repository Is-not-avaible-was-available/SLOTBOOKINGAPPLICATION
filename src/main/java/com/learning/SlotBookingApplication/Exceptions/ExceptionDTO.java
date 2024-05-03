package com.learning.SlotBookingApplication.Exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionDTO {
    private HttpStatus httpStatus;
    private String message;
}
