package com.learning.SlotBookingApplication.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.SlotBookingApplication.Models.BookedSlot;

public interface BookedSlotRepository extends JpaRepository<BookedSlot, Long>{
    
}
