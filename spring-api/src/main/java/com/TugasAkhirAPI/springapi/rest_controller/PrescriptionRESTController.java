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

import com.TugasAkhirAPI.springapi.model.PrescriptionModel;
import com.TugasAkhirAPI.springapi.rest.AppointmentDetail;
import com.TugasAkhirAPI.springapi.service.PrescriptionService;

// Notes: Please use english verb/adjective to describe your path
@RestController
@RequestMapping("/api/prescription")
public class PrescriptionRESTController {
    @Autowired
    PrescriptionService prescriptionService;


    @GetMapping(value="detail/{id}")
    private PrescriptionModel detailAppointment(@PathVariable("id") String id){
        try{
            return prescriptionService.findById(Long.parseLong(id));
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Prescription" + id + " not found");
        }
        
    }
}
