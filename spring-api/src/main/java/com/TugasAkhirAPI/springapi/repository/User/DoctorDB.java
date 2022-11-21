package com.TugasAkhirAPI.springapi.repository.User;

import com.TugasAkhirAPI.springapi.model.User.DoctorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorDB extends JpaRepository<DoctorModel, String> {
}
