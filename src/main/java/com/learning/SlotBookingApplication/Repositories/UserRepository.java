package com.learning.SlotBookingApplication.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.SlotBookingApplication.Models.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
    public Optional<User> findByEmail(String email);
}
