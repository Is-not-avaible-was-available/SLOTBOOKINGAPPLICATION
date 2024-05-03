package com.learning.SlotBookingApplication.Services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.learning.SlotBookingApplication.Exceptions.AlreadyExistsException;
import com.learning.SlotBookingApplication.Exceptions.InvalidCredentialException;
import com.learning.SlotBookingApplication.Exceptions.NotFoundException;
import com.learning.SlotBookingApplication.Models.Session;
import com.learning.SlotBookingApplication.Models.SessionStatus;
import com.learning.SlotBookingApplication.Models.User;
import com.learning.SlotBookingApplication.Repositories.SessionRepository;
import com.learning.SlotBookingApplication.Repositories.UserRepository;

@Service
public class AuthService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private Algorithm algorithm;
    private SessionRepository sessionRepository;
    public AuthService(UserRepository userRepository, SessionRepository sessionRepository){
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.algorithm = Algorithm.HMAC256("secretKeyForLoginToken");
    }
    public String createNewUser(String name, String email, String phoneNumber, String password) throws AlreadyExistsException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
       
        if(optionalUser.isPresent()){
            throw new AlreadyExistsException("Account already exists with :"+email);
        }else{
            User user = new User();
            user.setName(name);
            user.setPhoneNumber(phoneNumber);
            user.setCreatedAt(LocalDateTime.now());
            user.setLastModifiedAt(LocalDateTime.now());
            user.setEmail(email);
            user.setPassword(bCryptPasswordEncoder.encode(password));
             userRepository.save(user);
        }
       return "Successfully Signed In, welcome " +name+"!"; 
        
    }
    public String login(String email, String password) throws NotFoundException, InvalidCredentialException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()){
            throw new NotFoundException("email doesn't exists!");
        }

        User user = optionalUser.get();
        if(!bCryptPasswordEncoder.matches(user.getPassword(), password)){
            throw new InvalidCredentialException("Wrong password!");
        }

        List<String> claims = new ArrayList<>();
        claims.add(email);
        claims.add(user.getName());
        claims.add(user.getPhoneNumber());
        String token = JWT.create().withIssuer("SlotApplication").withSubject("jwtWebToken")
        .withClaim("userData", claims).withIssuedAt(new Date())
        .withExpiresAt(new Date(System.currentTimeMillis() + 500000l))
        .withJWTId(UUID.randomUUID().toString())
        .withNotBefore(new Date(System.currentTimeMillis() + 100000l)).sign(algorithm);

        Session session = new Session();
        session.setCreatedAt(LocalDateTime.now());
        session.setLastModifiedAt(LocalDateTime.now());
        session.setSessionStatus(SessionStatus.ACTIVE);
        session.setToken(token);
        session.setUser(user);

        sessionRepository.save(session);
       return token;
    }


    public SessionStatus validateToken(String token, Long userId) {
        Optional<Session> optionalSession = sessionRepository.findByTokenAndUser_ID(token, userId);
        if(optionalSession.isEmpty()){
            return SessionStatus.ENDED;
        }

        Session session = optionalSession.get();
        if(session.getSessionStatus().equals(SessionStatus.ENDED)){
            return SessionStatus.ENDED;
        }

        return SessionStatus.ACTIVE;
    }
    public String logout(String token, Long userId) {
        Optional<Session> optionalSession = sessionRepository.findByTokenAndUser_ID(token, userId);
        if(optionalSession.isEmpty()){
            return "logged out successfully!";
        }
        Session session = optionalSession.get();
        session.setSessionStatus(SessionStatus.ENDED);
        session.setLastModifiedAt(LocalDateTime.now());
        sessionRepository.save(session);
      return "You are logged out successfully!";
    }
    
}
