package com.TugasAkhir.spring.service.User;

import com.TugasAkhir.spring.repository.User.AdminDB;
import com.TugasAkhir.spring.repository.User.ApothecaryDB;
import com.TugasAkhir.spring.repository.User.DoctorDB;
import com.TugasAkhir.spring.repository.User.PatientDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    PatientDB patientDB;
    @Autowired
    DoctorDB doctorDB;
    @Autowired
    AdminDB adminDB;
    @Autowired
    ApothecaryDB apothecaryDB;
}
