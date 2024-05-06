package com.learning.SlotBookingApplication.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.SlotBookingApplication.DTOs.BookedSlotResponse;
import com.learning.SlotBookingApplication.Exceptions.NotFoundException;
import com.learning.SlotBookingApplication.Services.BookingService;

@RestController
@RequestMapping("/booking")
public class BookingController {
    private BookingService bookingService;

    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @GetMapping("/all-bookings")
    public ResponseEntity<List<BookedSlotResponse>> getAllBookedSlots(){
        List<BookedSlotResponse> bookings = bookingService.getAllBookedSlots();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @PostMapping("/{slotId}/{userId}/book")
    public ResponseEntity<String> bookNewSlot(@PathVariable Long userId,@PathVariable Long slotId) throws NotFoundException{
        String response = bookingService.bookNewSlot(userId, slotId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
}
