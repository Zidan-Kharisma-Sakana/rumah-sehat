package com.TugasAkhirAPI.springapi.rest_controller;
import com.TugasAkhirAPI.springapi.model.User.PatientModel;
import com.TugasAkhirAPI.springapi.service.User.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
// Notes: Please use english verb/adjective to describe your path
@RestController
@RequestMapping("/api/user")
public class UserRESTController {
    @Autowired
    PatientService patientService;
    @GetMapping("")
    @PreAuthorize("hasAuthority('PATIENT')")
    public PatientModel getPatientProfile(Principal principal){
        return patientService.getPatientByUsername(principal.getName());
    }
}
