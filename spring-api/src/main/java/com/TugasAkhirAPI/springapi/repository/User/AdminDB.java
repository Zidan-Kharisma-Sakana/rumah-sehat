package com.TugasAkhirAPI.springapi.repository.User;

import com.TugasAkhirAPI.springapi.model.User.AdminModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDB extends JpaRepository<AdminModel, String> {
}
