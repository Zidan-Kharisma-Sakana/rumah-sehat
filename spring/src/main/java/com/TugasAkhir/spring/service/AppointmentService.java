package com.TugasAkhir.spring.service;

import com.TugasAkhir.spring.model.AppointmentModel;
import com.TugasAkhir.spring.repository.AppointmentDB;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
