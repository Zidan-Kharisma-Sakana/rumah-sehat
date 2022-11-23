package com.TugasAkhir.spring.repository;


import com.TugasAkhir.spring.model.DrugPrescriptionModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface  DrugPrescriptionDB extends JpaRepository<DrugPrescriptionModel, String> {
}