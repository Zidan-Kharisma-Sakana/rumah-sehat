package com.TugasAkhir.spring.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.TugasAkhir.spring.model.AppointmentModel;


// Notes: Please use english verb/adjective to describe your path
@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    @GetMapping(value = "/detail/{id}")
    public String detailAppointment(@PathVariable String id,Model model, Principal principal){
        AppointmentModel appointment = appointmentService.findById(id);

        model.addAttribute("appointment",appointment);
        return "detail-appointment";
    }

    @PostMapping(value = "detail/{id}/save")
    public String saveAppointment(@PathVariable String id, Model model, Principal principal){
        appointmentService.add()
        return "detail-appointment";
    }


    
}
