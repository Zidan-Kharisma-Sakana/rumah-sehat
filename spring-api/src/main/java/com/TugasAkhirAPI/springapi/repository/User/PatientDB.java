package com.TugasAkhirAPI.springapi.repository.User;

import com.TugasAkhirAPI.springapi.model.User.BaseUser;
import com.TugasAkhirAPI.springapi.model.User.PatientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDB extends JpaRepository<PatientModel, String> {
    PatientModel findByUsername(String username);
}
