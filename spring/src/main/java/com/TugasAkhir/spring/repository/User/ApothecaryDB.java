package com.TugasAkhir.spring.repository.User;

import com.TugasAkhir.spring.model.User.ApothecaryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApothecaryDB extends JpaRepository<ApothecaryModel, String> {
}
