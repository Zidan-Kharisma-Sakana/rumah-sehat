package com.TugasAkhir.spring.service;

import com.TugasAkhir.spring.model.DrugPrescriptionModel;
import com.TugasAkhir.spring.model.PrescriptionModel;
import com.TugasAkhir.spring.repository.DrugPrescriptionDB;
import com.TugasAkhir.spring.repository.PrescriptionDB;

import java.util.List;

import org.hibernate.validator.internal.metadata.aggregated.rule.ParallelMethodsMustNotDefineGroupConversionForCascadedReturnValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrugPrescriptionService {
    @Autowired
    DrugPrescriptionDB drugPrescriptionDB;


    public DrugPrescriptionModel add(DrugPrescriptionModel drugPrescription){
        drugPrescriptionDB.save(drugPrescription);
        return drugPrescription;
    }

    public List<DrugPrescriptionModel> getListOfDrugdByPrescriptionId(Long id){
        return drugPrescriptionDB.findListDrugPrescription(id);
    }
}