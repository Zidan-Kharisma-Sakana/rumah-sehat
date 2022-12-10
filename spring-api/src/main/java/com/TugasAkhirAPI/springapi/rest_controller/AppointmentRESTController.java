package com.TugasAkhirAPI.springapi.rest_controller;

import java.security.Principal;
import java.util.List;

import java.util.NoSuchElementException;

import com.TugasAkhirAPI.springapi.dto.auth.LoginRequest;
import com.TugasAkhirAPI.springapi.dto.rayyan.AppointmentResponse;
import com.TugasAkhirAPI.springapi.dto.rayyan.CreateAppointmentRequest;
import com.TugasAkhirAPI.springapi.dto.rayyan.CreateAppointmentResponse;
import com.TugasAkhirAPI.springapi.model.User.DoctorModel;
import com.TugasAkhirAPI.springapi.model.User.PatientModel;
import com.TugasAkhirAPI.springapi.service.AppointmentService;
import com.TugasAkhirAPI.springapi.service.User.DoctorService;
import com.TugasAkhirAPI.springapi.service.User.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.TugasAkhirAPI.springapi.dto.mobile.AppointmentDetail;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.TugasAkhirAPI.springapi.restservice.AppointmentRestService;


// Notes: Please use english verb/adjective to describe your path
@RestController
@RequestMapping("/api/appointment")
public class AppointmentRESTController {
    @Autowired
    AppointmentRestService appointmentRestService;

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    PatientService patientService;

    @Autowired
    DoctorService doctorService;

    @GetMapping(value="detail/{code}")
    private AppointmentDetail detailAppointment(@PathVariable("code") String code){
        try{
            return appointmentRestService.findByCode(code);
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Code Course" + code + " not found");
        }
        
    }

    @GetMapping(value="/doctor")
    private List<DoctorModel> allDoctors(){
        return doctorService.findAll();
    }

    @GetMapping(value="/all")
    private List<AppointmentResponse> allAppointment(Principal principal){
        try{
            PatientModel patient = patientService.getPatientByUsername(principal.getName());
            return appointmentService.returnAppointments(patient);
        } catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    @PostMapping(value="/add")
    private CreateAppointmentResponse createAppointment(@Valid @RequestBody CreateAppointmentRequest request, BindingResult bindingResult, Principal principal){
        if(bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        }
        try{
            PatientModel patient = patientService.getPatientByUsername(principal.getName());
            DoctorModel doctor = doctorService.findByUuid(request.getDoctor_uuid());
            return appointmentService.createAppointment(request, patient, doctor);
        } catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }
    
}
