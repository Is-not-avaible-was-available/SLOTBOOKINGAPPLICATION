package com.learning.SlotBookingApplication.Services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.learning.SlotBookingApplication.DTOs.BookedSlotResponse;
import com.learning.SlotBookingApplication.Exceptions.NotFoundException;
import com.learning.SlotBookingApplication.Mappers.SlotMapper;
import com.learning.SlotBookingApplication.Models.BookedSlot;
import com.learning.SlotBookingApplication.Models.Slot;
import com.learning.SlotBookingApplication.Models.SlotStatus;
import com.learning.SlotBookingApplication.Models.User;
import com.learning.SlotBookingApplication.Repositories.BookedSlotRepository;
import com.learning.SlotBookingApplication.Repositories.SlotRepository;
import com.learning.SlotBookingApplication.Repositories.UserRepository;

@Service
public class BookingService {
    private BookedSlotRepository bookedSlotRepository;
    private UserRepository userRepository;
    private SlotRepository slotRepository;
    public BookingService(BookedSlotRepository bookedSlotRepository, UserRepository userRepository,
    SlotRepository slotRepository){
        this.bookedSlotRepository = bookedSlotRepository;
        this.userRepository = userRepository;
        this.slotRepository = slotRepository;
    }

    public List<BookedSlotResponse> getAllBookedSlots() {
       List<BookedSlot> bookedSlots = bookedSlotRepository.findAll();
       List<BookedSlotResponse> bookedSlotResponses = new ArrayList<>();
       for(BookedSlot bookedSlot : bookedSlots){
        BookedSlotResponse bookedSlotResponse = SlotMapper.mapBookedSlotToBookedSlotResponse(bookedSlot);
        bookedSlotResponses.add(bookedSlotResponse);
       }
       return bookedSlotResponses;
    }

    public String bookNewSlot(Long userId, Long slotId) throws NotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Slot> slotOptional = slotRepository.findById(slotId);
        if(userOptional.isEmpty() || slotOptional.isEmpty()){
            throw new NotFoundException("not found!");
        }
        User user = userOptional.get();
        Slot slot = slotOptional.get();
        if(!slot.getSlotStatus().equals(SlotStatus.AVAILABLE)){
            throw new NotFoundException("Slot not available");
        }
        slot.setSlotStatus(SlotStatus.BOOKED);

        BookedSlot bookedSlot = new BookedSlot();
        bookedSlot.setBookedBy(user);
        bookedSlot.setCreatedAt(LocalDateTime.now());
        bookedSlot.setLastModifiedAt(LocalDateTime.now());
        bookedSlot.setSlot(slot);
        bookedSlot.setStartTime(slot.getStartDate());
        bookedSlot.setEndTime(slot.getEndDate());
        bookedSlotRepository.save(bookedSlot);
        slotRepository.save(slot);
        return "Booked";
    }
    
}
