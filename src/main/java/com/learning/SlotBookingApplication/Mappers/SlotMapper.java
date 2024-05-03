package com.learning.SlotBookingApplication.Mappers;

import com.learning.SlotBookingApplication.DTOs.SlotResponse;
import com.learning.SlotBookingApplication.Models.Slot;

public class SlotMapper {
    
    public static SlotResponse mapSlotToSlotResponse(Slot slot){
        SlotResponse slotResponse = new SlotResponse();
        slotResponse.setEndTime(slot.getEndDate().toString());
        slotResponse.setStartTime(slot.getStartDate().toString());
        slotResponse.setId(slot.getId());
        return slotResponse;
    }
}
