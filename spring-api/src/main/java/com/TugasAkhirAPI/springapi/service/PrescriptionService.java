package com.TugasAkhirAPI.springapi.service;

import com.TugasAkhirAPI.springapi.model.PrescriptionModel;
import com.TugasAkhirAPI.springapi.repository.PrescriptionDB;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionService {
    @Autowired
    PrescriptionDB prescriptionDB;

    public PrescriptionModel findById(Long id){
        Optional<PrescriptionModel> prescription = prescriptionDB.findById(id);

        if(prescription.isPresent()){
            return prescription.get();
        }   return null;
    
}

}
