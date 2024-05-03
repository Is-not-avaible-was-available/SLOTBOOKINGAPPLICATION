package com.learning.SlotBookingApplication.Services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.learning.SlotBookingApplication.DTOs.SlotResponse;
import com.learning.SlotBookingApplication.Mappers.SlotMapper;
import com.learning.SlotBookingApplication.Models.Slot;
import com.learning.SlotBookingApplication.Models.SlotStatus;
import com.learning.SlotBookingApplication.Repositories.SlotRepository;

@Service
public class SlotService {
    private SlotRepository slotRepository;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public SlotService(SlotRepository slotRepository){
        this.slotRepository = slotRepository;
    }

    public SlotResponse createNewSlot(String startTime, String endTime) {
        
        LocalDateTime start = LocalDateTime.parse(startTime, dateTimeFormatter);
        LocalDateTime end = LocalDateTime.parse(endTime, dateTimeFormatter);
        Slot slot = new Slot();
        slot.setCreatedAt(LocalDateTime.now());
        slot.setLastModifiedAt(LocalDateTime.now());
        slot.setSlotStatus(SlotStatus.AVAILABLE);
        slot.setStartDate(start);
        slot.setEndDate(end);
        
        Slot saved = slotRepository.save(slot);
       return SlotMapper.mapSlotToSlotResponse(saved);
    }

    public List<SlotResponse> getAllSlots() {
        List<Slot> slots = slotRepository.findAll();
        // List<SlotResponse> slotsList = new ArrayList<>();
        // for(Slot slot: slots){
        //     slotsList.add(SlotMapper.mapSlotToSlotResponse(slot));
        // }
        List<SlotResponse> slotsList = slots.stream().map(SlotMapper::mapSlotToSlotResponse).toList();
        return slotsList;
    }

    public List<SlotResponse> getAvailableSlots() {
      List<Slot> slots = slotRepository.findAll();
      List<SlotResponse> slotsList = new ArrayList<>();
        for(Slot slot: slots){
            if(slot.getSlotStatus().equals(SlotStatus.AVAILABLE))
            slotsList.add(SlotMapper.mapSlotToSlotResponse(slot));
        }
        return slotsList;
    }
    
}
