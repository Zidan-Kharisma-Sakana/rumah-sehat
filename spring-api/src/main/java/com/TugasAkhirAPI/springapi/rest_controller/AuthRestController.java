package com.TugasAkhirAPI.springapi.rest_controller;

import com.TugasAkhirAPI.springapi.dto.auth.LoginRequest;
import com.TugasAkhirAPI.springapi.dto.auth.LoginResponse;
import com.TugasAkhirAPI.springapi.dto.auth.RegisterRequest;
import com.TugasAkhirAPI.springapi.model.User.BaseUser;
import com.TugasAkhirAPI.springapi.model.User.PatientModel;
import com.TugasAkhirAPI.springapi.security.JwtUtils;
import com.TugasAkhirAPI.springapi.service.DummyService;
import com.TugasAkhirAPI.springapi.service.User.PatientService;
import com.TugasAkhirAPI.springapi.service.User.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    @Autowired
    PatientService patientService;
    @Autowired
    DummyService dummyService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;

    @GetMapping("/dummy")
    private String createDummyUser(){
        try {
            log.info("Try to creating dummy data");
            dummyService.createDummyAdmin();
            dummyService.createDummyApothecary();
            dummyService.createDummyDoctor();
            dummyService.createDummyPatient();
            dummyService.createDummyInvoice();
            dummyService.createDummyDrug();
        } catch (Exception e){
            return e.getLocalizedMessage();
        }
        return "OK";
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        }
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            BaseUser user = userService.findByUsername(userDetails.getUsername());
            log.info("Login successful");
            return ResponseEntity.ok(new LoginResponse(user.getUsername(), user.getEmail(), jwt));
        }catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "error"
            );
        }

    }

    @PostMapping("/signup")
    public ResponseEntity<?> patientRegistration(@Valid @RequestBody RegisterRequest form, BindingResult bindingResult) {
        System.out.println("SIGN UP");
        if(bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        }


        PatientModel patient = null;
        try {
            System.out.println(form.getUsername());
            System.out.println(form.getName());
            patient = patientService.addPatient(form);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getLocalizedMessage()
            );
        }
        return ResponseEntity.ok(patient);
    }
}
