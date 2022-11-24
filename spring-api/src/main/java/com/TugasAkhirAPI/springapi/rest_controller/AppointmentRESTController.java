package com.TugasAkhirAPI.springapi.rest_controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.TugasAkhirAPI.springapi.rest.AppointmentDetail;
import com.TugasAkhirAPI.springapi.restservice.AppointmentRestService;


// Notes: Please use english verb/adjective to describe your path
@RestController
@RequestMapping("/api/appointment")
public class AppointmentRESTController {
    @Autowired
    AppointmentRestService appointmentRestService;
    @GetMapping(value="detail/{code}")
    private AppointmentDetail detailAppointment(@PathVariable("code") String code){
        try{
            return appointmentRestService.findByCode(code);
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Code Course" + code + " not found");
        }
        
    }
    
}
