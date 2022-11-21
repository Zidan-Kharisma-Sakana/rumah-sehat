package com.TugasAkhirAPI.springapi.repository.User;

import com.TugasAkhirAPI.springapi.model.User.ApothecaryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApothecaryDB extends JpaRepository<ApothecaryModel, String> {
}
