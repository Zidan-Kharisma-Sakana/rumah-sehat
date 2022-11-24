package com.TugasAkhirAPI.springapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TugasAkhirAPI.springapi.model.AppointmentModel;
import com.TugasAkhirAPI.springapi.repository.AppointmentDB;

@Service
public class AppointmentService {
    @Autowired
    AppointmentDB appointmentDB;

    public AppointmentModel update(AppointmentModel appointment){
        appointmentDB.save(appointment);
        return appointment;
    }

    public AppointmentModel findByCode(String code){
        Optional<AppointmentModel> appointment = appointmentDB.findByCode(code);
        if(!appointment.isPresent()){
            return null;
        } else return appointment.get();
    }

}
