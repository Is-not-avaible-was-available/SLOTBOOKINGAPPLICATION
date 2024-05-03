package com.learning.SlotBookingApplication.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.SlotBookingApplication.Models.Slot;

public interface SlotRepository extends JpaRepository<Slot, Long>{

    
}

