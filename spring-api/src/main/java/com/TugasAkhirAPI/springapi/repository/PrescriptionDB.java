package com.TugasAkhirAPI.springapi.repository;

import com.TugasAkhirAPI.springapi.model.PrescriptionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionDB extends JpaRepository<PrescriptionModel, Long> {
}
