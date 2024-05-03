package com.learning.SlotBookingApplication.Models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Slot extends BaseModel{   
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @Enumerated
    private SlotStatus slotStatus;
}
