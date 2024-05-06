package com.learning.SlotBookingApplication.Mappers;

import com.learning.SlotBookingApplication.DTOs.BookedSlotResponse;
import com.learning.SlotBookingApplication.DTOs.SlotResponse;
import com.learning.SlotBookingApplication.Models.BookedSlot;
import com.learning.SlotBookingApplication.Models.Slot;

public class SlotMapper {
    
    public static SlotResponse mapSlotToSlotResponse(Slot slot){
        SlotResponse slotResponse = new SlotResponse();
        slotResponse.setEndTime(slot.getEndDate().toString());
        slotResponse.setStartTime(slot.getStartDate().toString());
        slotResponse.setId(slot.getId());
        return slotResponse;
    }

    public static BookedSlotResponse mapBookedSlotToBookedSlotResponse(BookedSlot bookedSlot){
        BookedSlotResponse bookedSlotResponse = new BookedSlotResponse();
        bookedSlotResponse.setEndDate(bookedSlot.getEndTime().toString());
        bookedSlotResponse.setStartDate(bookedSlot.getStartTime().toString());
        bookedSlotResponse.setUserName(bookedSlot.getBookedBy().getName());
        bookedSlotResponse.setSlotId(bookedSlot.getId());
        return bookedSlotResponse;
    }
}
