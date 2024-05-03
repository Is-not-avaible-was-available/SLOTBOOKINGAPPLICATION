package com.learning.SlotBookingApplication.DTOs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ValidateTokenRequestDTO {
    private String token;
    private Long userId;
}
