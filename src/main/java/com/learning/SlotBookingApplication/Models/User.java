package com.learning.SlotBookingApplication.Models;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User extends BaseModel{
    private  String name;
    private String email;
    private String password;
    private String phoneNumber;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bookedBy")
    private List<BookedSlot> bookings;
}
