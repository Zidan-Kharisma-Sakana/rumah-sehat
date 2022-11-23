package com.TugasAkhirAPI.springapi.service.User;


import com.TugasAkhirAPI.springapi.model.User.BaseUser;
import com.TugasAkhirAPI.springapi.repository.User.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Service
public class UserService {
    @Autowired
    PatientDB patientDB;
    @Autowired
    DoctorDB doctorDB;
    @Autowired
    AdminDB adminDB;
    @Autowired
    ApothecaryDB apothecaryDB;

    @Autowired
    UserDB userDB;

    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    public BaseUser findByUsername(String username){
        return userDB.findByUsername(username);
    }
}
