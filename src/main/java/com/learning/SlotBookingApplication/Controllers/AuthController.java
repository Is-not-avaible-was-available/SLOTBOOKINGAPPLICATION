package com.learning.SlotBookingApplication.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.SlotBookingApplication.DTOs.LoginRequestDTO;
import com.learning.SlotBookingApplication.DTOs.SignUpResquestDTO;
import com.learning.SlotBookingApplication.DTOs.ValidateTokenRequestDTO;
import com.learning.SlotBookingApplication.Exceptions.AlreadyExistsException;
import com.learning.SlotBookingApplication.Exceptions.InvalidCredentialException;
import com.learning.SlotBookingApplication.Exceptions.NotFoundException;
import com.learning.SlotBookingApplication.Models.SessionStatus;
import com.learning.SlotBookingApplication.Services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }
    
    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody SignUpResquestDTO signUpResquestDTO) throws AlreadyExistsException{
        String response = authService.createNewUser(signUpResquestDTO.getName(), signUpResquestDTO.getEmail(),
        signUpResquestDTO.getPhoneNumber(), signUpResquestDTO.getPassword());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO requestDTO) throws NotFoundException, InvalidCredentialException{
        String token = authService.login(requestDTO.getEmail(), requestDTO.getPassword());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody ValidateTokenRequestDTO requestDTO) throws NotFoundException{
        String response  = authService.logout(requestDTO.getToken(), requestDTO.getUserId());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/validate")
    public ResponseEntity<SessionStatus> validate(@RequestBody ValidateTokenRequestDTO requestDTO) throws NotFoundException{

        SessionStatus sessionStatus = authService.validateToken(requestDTO.getToken(), requestDTO.getUserId());
        return ResponseEntity.ok(sessionStatus);
    }
}
