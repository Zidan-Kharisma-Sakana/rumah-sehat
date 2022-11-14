package com.TugasAkhir.spring.service.User;

import com.TugasAkhir.spring.repository.User.PatientDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    @Autowired
    PatientDB patientDB;
}
