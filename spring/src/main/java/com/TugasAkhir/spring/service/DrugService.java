package com.TugasAkhir.spring.service;

import com.TugasAkhir.spring.model.DrugModel;
import com.TugasAkhir.spring.repository.DrugDB;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrugService {
    @Autowired
    DrugDB drugDB;

    public List<DrugModel> viewAll(){
        return drugDB.findAll();
    }
}
