package com.TugasAkhirAPI.springapi.service;



import java.util.ArrayList;
import java.util.List;

import org.hibernate.event.internal.DefaultInitializeCollectionEventListener;
import org.hibernate.validator.internal.metadata.aggregated.rule.ParallelMethodsMustNotDefineGroupConversionForCascadedReturnValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TugasAkhirAPI.springapi.dto.mobile.DrugPrescriptionDetail;
import com.TugasAkhirAPI.springapi.model.DrugPrescriptionModel;
import com.TugasAkhirAPI.springapi.repository.DrugPrescriptionDB;

@Service
public class DrugPrescriptionService {
    @Autowired
    DrugPrescriptionDB drugPrescriptionDB;


    


    public List<DrugPrescriptionDetail> getListOfDrugdByPrescriptionId(Long id){
        List<DrugPrescriptionModel> listDrug = drugPrescriptionDB.findListDrugPrescription(id);
        List<DrugPrescriptionDetail> listDrugJson = new ArrayList<>();

        if(!listDrug.isEmpty()){
            for(DrugPrescriptionModel drug : listDrug){
                DrugPrescriptionDetail drugJson = new DrugPrescriptionDetail();
                
                drugJson.setNama(drug.getDrug().getName());
                drugJson.setQuantity(drug.getQuantity());
                listDrugJson.add(drugJson);
            }
        }
        

        return listDrugJson;
    }
}