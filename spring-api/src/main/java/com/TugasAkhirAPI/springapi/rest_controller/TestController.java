package com.TugasAkhirAPI.springapi.rest_controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/patient")
    @PreAuthorize("hasAuthority('PATIENT')")
    public String patientAccess() {
        return "Patient Access.";
    }

    @GetMapping("/web")
    @PreAuthorize("hasAuthority('DOCTOR') or hasAuthority('APOTHECARY') or hasAuthority('ADMIN')")
    public String doctorAndApothecaryAndAdminAccess(Principal principal) {
        String username = principal.getName();
        System.out.println(username);
        return "Web Access.";
    }
}
