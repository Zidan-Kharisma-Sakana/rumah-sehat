package com.TugasAkhirAPI.springapi.restservice;

import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TugasAkhirAPI.springapi.dto.mobile.AppointmentDetail;
import com.TugasAkhirAPI.springapi.model.AppointmentModel;
import com.TugasAkhirAPI.springapi.repository.AppointmentDB;




@Service
public class AppointmentRestService {
    @Autowired
    AppointmentDB appointmentDB;
    
    public AppointmentDetail findByCode(String code){
        Optional<AppointmentModel> appointment = appointmentDB.findByCode(code);

        if(!appointment.isPresent()){
            throw new NoSuchElementException();
        } else{
            AppointmentModel appointmentFound = appointment.get();
            AppointmentDetail appointmentDetail = new AppointmentDetail();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");

            appointmentDetail.setCode(appointmentFound.getCode());
            appointmentDetail.setWaktuAwal(appointmentFound.getStartTime().format(formatter));
            appointmentDetail.setNamaDokter(appointmentFound.getDoctor().getName());
            appointmentDetail.setNamaPasien(appointmentFound.getPatient().getName());

            if(appointmentFound.getIsDone()){
                appointmentDetail.setStatus("Selesai");
            } else{appointmentDetail.setStatus("Belum Selesai");}

            if(appointmentFound.getPrescription()==null){
                appointmentDetail.setIdPrescription(Long.parseLong("0"));
            } else{appointmentDetail.setIdPrescription(appointmentFound.getPrescription().getId());}

            return appointmentDetail;

        
        }
    }
}