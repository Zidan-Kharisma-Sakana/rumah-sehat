package com.TugasAkhirAPI.springapi.service.User;

import com.TugasAkhirAPI.springapi.repository.AppointmentDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApothecaryService {
    @Autowired
    AppointmentDB appointmentDB;
}
