package com.TugasAkhir.spring.service;

import com.TugasAkhir.spring.repository.DrugDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrugService {
    @Autowired
    DrugDB drugDB;
}
