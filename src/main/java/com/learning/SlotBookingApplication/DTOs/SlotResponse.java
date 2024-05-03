package com.learning.SlotBookingApplication.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SlotResponse {
    private Long id;
    private String startTime;
    private String endTime;
}
