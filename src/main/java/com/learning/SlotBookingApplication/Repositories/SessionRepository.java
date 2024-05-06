package com.learning.SlotBookingApplication.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.learning.SlotBookingApplication.Models.Session;
import com.learning.SlotBookingApplication.Models.User;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long>{
    Optional<Session> findByTokenAndUser(String token, User user);
}
