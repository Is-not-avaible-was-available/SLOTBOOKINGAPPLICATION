package com.learning.SlotBookingApplication.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookedSlotResponse {
    private String startDate;
    private  String endDate;
    private String userName;
    private Long slotId;
}
