package com.TugasAkhir.spring.service;

import com.TugasAkhir.spring.repository.AppointmentDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {
    @Autowired
    AppointmentDB appointmentDB;
}
