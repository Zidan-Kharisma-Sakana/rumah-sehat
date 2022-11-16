package com.TugasAkhir.spring.service;

import com.TugasAkhir.spring.repository.PrescriptionDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionService {
    @Autowired
    PrescriptionDB prescriptionDB;
}
