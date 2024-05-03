package com.learning.SlotBookingApplication.DTOs;

import lombok.Setter;
import lombok.Getter;
@Getter
@Setter
public class CreateSlotRequestDTO {
    private String startTime;
    private String endTime;
}
