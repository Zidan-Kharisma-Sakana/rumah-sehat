package com.TugasAkhir.spring.repository;

import com.TugasAkhir.spring.model.PrescriptionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionDB extends JpaRepository<PrescriptionModel, Long> {
}
