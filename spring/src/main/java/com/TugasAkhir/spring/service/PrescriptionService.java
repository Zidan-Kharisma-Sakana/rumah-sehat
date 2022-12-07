package com.TugasAkhir.spring.service;

import com.TugasAkhir.spring.model.PrescriptionModel;
import com.TugasAkhir.spring.repository.PrescriptionDB;

import java.util.List;
import java.util.Optional;

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

    public PrescriptionModel update(PrescriptionModel prescription){
        prescriptionDB.save(prescription);
        return prescription;
    }

    public PrescriptionModel findById(Long id){
        Optional<PrescriptionModel> prescription = prescriptionDB.findById(id);

        if(prescription.isPresent()){
            return prescription.get();
        }   return null;
    }

    public List<PrescriptionModel> findAll(){
        return prescriptionDB.findAll();
    }
}
