package com.TugasAkhirAPI.springapi.service;

import com.TugasAkhirAPI.springapi.model.User.AdminModel;
import com.TugasAkhirAPI.springapi.model.User.ApothecaryModel;
import com.TugasAkhirAPI.springapi.model.User.DoctorModel;
import com.TugasAkhirAPI.springapi.model.User.PatientModel;
import com.TugasAkhirAPI.springapi.repository.User.AdminDB;
import com.TugasAkhirAPI.springapi.repository.User.ApothecaryDB;
import com.TugasAkhirAPI.springapi.repository.User.DoctorDB;
import com.TugasAkhirAPI.springapi.repository.User.PatientDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DummyService {
    @Autowired
    PatientDB patientDB;
    @Autowired
    DoctorDB doctorDB;
    @Autowired
    AdminDB adminDB;
    @Autowired
    ApothecaryDB apothecaryDB;

    public void createDummyPatient (){
        PatientModel patient = new PatientModel();
        patient.setBalance(0L);
        patient.setName("Pasien");
        patient.setUsername("pasien");
        patient.setEmail("pasien@gmail.com");
        patient.setPassword(encrypt("pasien"));
        patient.setAge(10L);
        patient.setRole("PATIENT");
        patientDB.save(patient);
    }

    public void createDummyDoctor(){
        DoctorModel doctor = new DoctorModel();
        doctor.setUsername("dokter");
        doctor.setEmail("dokter@gmail.com");
        doctor.setName("Dokter");
        doctor.setRole("DOCTOR");
        doctor.setFee(20000L);
        doctor.setPassword(encrypt("dokter"));
        doctorDB.save(doctor);
    }

    public void createDummyApothecary(){
        ApothecaryModel apothecary = new ApothecaryModel();
        apothecary.setUsername("apoteker");
        apothecary.setEmail("apoteker@gmail.com");
        apothecary.setName("Apoteker");
        apothecary.setRole("APOTHECARY");
        apothecary.setPassword(encrypt("apoteker"));
        apothecaryDB.save(apothecary);
    }

    public void createDummyAdmin(){
        AdminModel admin = new AdminModel();
        admin.setName("Admin");
        admin.setRole("ADMIN");
        admin.setEmail("admin@gmail.com");
        admin.setUsername("admin");
        admin.setPassword(encrypt("admin"));
        adminDB.save(admin);
    }

    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }
}
