package com.learning.SlotBookingApplication.Controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.SlotBookingApplication.DTOs.CreateSlotRequestDTO;
import com.learning.SlotBookingApplication.DTOs.SlotResponse;
import com.learning.SlotBookingApplication.Services.SlotService;

@RestController
@RequestMapping("/slots")
public class SlotController {
    private SlotService slotService;

    public SlotController(SlotService slotService){
        this.slotService = slotService;
    }


    @GetMapping("/all-slots")
    public ResponseEntity<List<SlotResponse>> getAllSlots(){
        List<SlotResponse> slotList = slotService.getAllSlots();
        return ResponseEntity.ok(slotList);
    }

    @PostMapping()
    public ResponseEntity<SlotResponse> createSlot(@RequestBody CreateSlotRequestDTO createSlotRequestDTO){
        SlotResponse newSlot = slotService.createNewSlot(createSlotRequestDTO.getStartTime(), 
        createSlotRequestDTO.getEndTime());
        return new ResponseEntity<>(newSlot, HttpStatus.CREATED);
    }

    @GetMapping("/available")
    public ResponseEntity<List<SlotResponse>> getAllAvailableSlots(){
        List<SlotResponse> slots = slotService.getAvailableSlots();
        return ResponseEntity.ok(slots);
    }
}
