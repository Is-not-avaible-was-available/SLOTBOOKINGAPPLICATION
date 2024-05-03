package com.learning.SlotBookingApplication.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.learning.SlotBookingApplication.Models.Session;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long>{
    Optional<Session> findByTokenAndUser_ID(String token, Long userId);
}
