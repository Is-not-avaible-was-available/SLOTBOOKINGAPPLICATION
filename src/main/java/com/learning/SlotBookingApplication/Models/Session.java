package com.learning.SlotBookingApplication.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Session extends BaseModel{
    @Column(length = 500)
    private String token;
    @Enumerated
    private SessionStatus sessionStatus;
    @ManyToOne
    private User user;
}
