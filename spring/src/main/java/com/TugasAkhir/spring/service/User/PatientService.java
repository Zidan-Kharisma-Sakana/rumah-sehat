package com.TugasAkhir.spring.service.User;

import com.TugasAkhir.spring.model.User.PatientModel;
import com.TugasAkhir.spring.repository.User.PatientDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    PatientDB patientDB;

    public List<PatientModel> findAll() {
        return patientDB.findAll();
    }
}
