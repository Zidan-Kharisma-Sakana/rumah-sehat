package com.TugasAkhirAPI.springapi.service;

import com.TugasAkhirAPI.springapi.repository.DrugDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrugService {
    @Autowired
    DrugDB drugDB;
}
