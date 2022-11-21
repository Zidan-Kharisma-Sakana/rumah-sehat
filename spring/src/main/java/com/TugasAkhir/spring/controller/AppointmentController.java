package com.TugasAkhir.spring.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.TugasAkhir.spring.model.AppointmentModel;
import com.TugasAkhir.spring.service.AppointmentService;


// Notes: Please use english verb/adjective to describe your path
@Controller
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;
    
    //@Autowired
    //UserService userService;
    
    @GetMapping(value = "/detail/{id}")
    public String detailAppointment(@PathVariable String id,Model model, Principal principal){
        AppointmentModel appointment = appointmentService.findById(id);
        // role = ...

        if(appointment==null) {
            return "gagal-view-appointment";
            
        }

        model.addAttribute("appointment",appointment);
        return "detail-appointment";
    }

    @PostMapping(value = "detail/{id}/save")
    public String saveAppointment(@PathVariable String id, Model model, Principal principal){
        AppointmentModel appointment = appointmentService.findById(id);
        int status = 0;
        if(appointment==null) {
            status=1;
            model.addAttribute("status", status);
            return "gagal-save-appointment";
            
        }

        if(appointment.getPrescription()!= null){
            if(!appointment.getPrescription().getIsDone()){
                status=2;
                model.addAttribute("status", status);
                return "gagal-save-appointment";
            } else if (appointment.getIsDone()){
                status=3;
                model.addAttribute("status", status);
                return "gagal-save-appointment";
            }
        } else {
            if(appointment.getIsDone()){
                status=3;
                model.addAttribute("status", status);
                return "gagal-save-appointment";
            }
        }
        model.addAttribute("appointment", appointment);
        model.addAttribute("status", status);
        
        appointment.setIsDone(true);
        appointmentService.update(appointment);

        return "save-appointment";
    }


    
}
