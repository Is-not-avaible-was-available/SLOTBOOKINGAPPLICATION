package com.learning.SlotBookingApplication.Models;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
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
    @ManyToOne(cascade = CascadeType.ALL)
    private User bookedBy;
    @ManyToOne(cascade = CascadeType.ALL)
    private Slot slot;
}
