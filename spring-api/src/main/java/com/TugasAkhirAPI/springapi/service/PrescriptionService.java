package com.TugasAkhirAPI.springapi.service;

import com.TugasAkhirAPI.springapi.repository.PrescriptionDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionService {
    @Autowired
    PrescriptionDB prescriptionDB;
}
