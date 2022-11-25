package com.TugasAkhir.spring.repository.User;

import com.TugasAkhir.spring.model.User.DoctorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorDB extends JpaRepository<DoctorModel, String> {
    DoctorModel findByUsername(String username);
}
