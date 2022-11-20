package com.TugasAkhir.spring.service;

import com.TugasAkhir.spring.model.PrescriptionModel;
import com.TugasAkhir.spring.repository.PrescriptionDB;

import org.hibernate.validator.internal.metadata.aggregated.rule.ParallelMethodsMustNotDefineGroupConversionForCascadedReturnValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionService {
    @Autowired
    PrescriptionDB prescriptionDB;


    public PrescriptionModel add(PrescriptionModel prescription){
        prescriptionDB.save(prescription);
        return prescription;
    }
}
