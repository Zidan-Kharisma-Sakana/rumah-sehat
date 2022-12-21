package com.TugasAkhirAPI.springapi.service;

import com.TugasAkhirAPI.springapi.dto.auth.RegisterRequest;
import com.TugasAkhirAPI.springapi.model.User.PatientModel;
import com.TugasAkhirAPI.springapi.repository.User.PatientDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    @Autowired
    PatientDB patientDB;

    public String encrypt(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
    public PatientModel addPatient(RegisterRequest form){
        PatientModel patient = new PatientModel();
        patient.setAge(form.getAge());
        patient.setPassword(encrypt(form.getPassword()));
        patient.setEmail(form.getEmail());
        patient.setBalance(0L);
        patient.setName(form.getName());
        patient.setUsername(form.getUsername());
        patient.setRole("PATIENT");
        return patientDB.save(patient);
    }

    public PatientModel update(PatientModel patient){
        patientDB.save(patient);
        return patient;
    }
    public PatientModel getPatientByUsername(String username){
        return patientDB.findByUsername(username);
    }
}
