package com.TugasAkhir.spring.service.User;

import com.TugasAkhir.spring.repository.AppointmentDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApothecaryService {
    @Autowired
    AppointmentDB appointmentDB;
}
