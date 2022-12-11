package com.TugasAkhirAPI.springapi.service;

import com.TugasAkhirAPI.springapi.model.AppointmentModel;
import com.TugasAkhirAPI.springapi.model.DrugModel;
import com.TugasAkhirAPI.springapi.model.InvoiceModel;
import com.TugasAkhirAPI.springapi.model.User.AdminModel;
import com.TugasAkhirAPI.springapi.model.User.ApothecaryModel;
import com.TugasAkhirAPI.springapi.model.User.DoctorModel;
import com.TugasAkhirAPI.springapi.model.User.PatientModel;
import com.TugasAkhirAPI.springapi.repository.AppointmentDB;
import com.TugasAkhirAPI.springapi.repository.DrugDB;
import com.TugasAkhirAPI.springapi.repository.InvoiceDB;
import com.TugasAkhirAPI.springapi.repository.User.AdminDB;
import com.TugasAkhirAPI.springapi.repository.User.ApothecaryDB;
import com.TugasAkhirAPI.springapi.repository.User.DoctorDB;
import com.TugasAkhirAPI.springapi.repository.User.PatientDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    @Autowired
    InvoiceDB invoiceDB;

    @Autowired
    DrugDB drugDB;

    @Autowired
    AppointmentDB appointmentDB;
    
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

    public void createDummyDrug(){
        DrugModel drug = new DrugModel();
        drug.setName("Paracetamol");
        drug.setId("1");
        drug.setStock(Long.valueOf(15));
        drug.setPrice(Long.valueOf(12000));
        drugDB.save(drug);
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

    public void createDummyInvoice(){
        InvoiceModel invoice = new InvoiceModel();
        invoice.setCode("BILL-1");
        invoice.setAmount(1000L);
        invoice.setDateIssued(LocalDateTime.now());
        invoice.setDatePaid(LocalDateTime.now());
        invoice.setIsPaid(true);
        invoiceDB.save(invoice);

        InvoiceModel invoice2 = new InvoiceModel();
        invoice2.setCode("BILL-2");
        invoice2.setAmount(2000L);
        invoice2.setDateIssued(LocalDateTime.now());
        invoice2.setDatePaid(LocalDateTime.now());
        invoice2.setIsPaid(true);
        invoiceDB.save(invoice2);

        List<PatientModel> listPatient = patientDB.findAll();
        List<DoctorModel> listDoctor = doctorDB.findAll();

        AppointmentModel appointment = new AppointmentModel();
        appointment.setInvoice(invoice);
        appointment.setCode("APT-1");
        appointment.setPatient(listPatient.get(0));
        appointment.setDoctor(listDoctor.get(0));
        appointment.setStartTime(LocalDateTime.now());
        appointment.setIsDone(false);
        appointmentDB.save(appointment);

        AppointmentModel appointment2 = new AppointmentModel();
        appointment2.setInvoice(invoice2);
        appointment2.setCode("APT-2");
        appointment2.setPatient(listPatient.get(0));
        appointment2.setDoctor(listDoctor.get(0));
        appointment2.setStartTime(LocalDateTime.now());
        appointment2.setIsDone(false);
        appointmentDB.save(appointment2);

        invoice.setAppointment(appointment);
        invoice2.setAppointment(appointment2);
        invoiceDB.save(invoice);
        invoiceDB.save(invoice2);
    }

    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }
}
