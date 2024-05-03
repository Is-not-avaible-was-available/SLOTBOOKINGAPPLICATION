package com.learning.SlotBookingApplication.Models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class BookedSlot extends BaseModel{ 
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @ManyToOne
    private User bookedBy;
    @ManyToOne
    private Slot slot;
}
