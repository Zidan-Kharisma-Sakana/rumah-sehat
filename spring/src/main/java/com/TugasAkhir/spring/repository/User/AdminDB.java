package com.TugasAkhir.spring.repository.User;

import com.TugasAkhir.spring.model.User.AdminModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDB extends JpaRepository<AdminModel, String> {
}
