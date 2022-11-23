package com.TugasAkhir.spring.repository.User;

import com.TugasAkhir.spring.model.User.PatientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDB extends JpaRepository<PatientModel, String> {
}
