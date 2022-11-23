package com.TugasAkhir.spring.repository;

import com.TugasAkhir.spring.model.PrescriptionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionDB extends JpaRepository<PrescriptionModel, Long> {
}
