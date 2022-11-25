package com.TugasAkhir.spring.service;

import com.TugasAkhir.spring.model.DrugModel;
import com.TugasAkhir.spring.repository.DrugDB;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.TugasAkhir.spring.model.DrugModel;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Setter
@Getter
@Transactional
public class DrugService {
    @Autowired
    DrugDB drugDB;

    public List<DrugModel> getListDrug(){
        return drugDB.findAll();
    }

    public DrugModel getDrug (String id){
        return drugDB.getReferenceById(id);
    }

    public DrugModel updateDrug (DrugModel drug){
        drugDB.save(drug);
        return drug;
    }


    public List<DrugModel> viewAll(){
        return drugDB.findAll();
    }


}
