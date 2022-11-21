package com.TugasAkhir.spring.rest_controller;

import com.TugasAkhir.spring.dto.auth.LoginResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {
    @GetMapping("/test")
    private LoginResponse login(){
        System.out.println("HAHAHAHAHHAHAHAHHAHA");
        return new LoginResponse("Nama","Shioriko@Mifune.com", "inijwtToken");
    }
}
