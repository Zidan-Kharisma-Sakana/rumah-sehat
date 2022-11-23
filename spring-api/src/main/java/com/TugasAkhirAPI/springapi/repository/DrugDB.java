package com.TugasAkhirAPI.springapi.repository;

import com.TugasAkhirAPI.springapi.model.DrugModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  DrugDB extends JpaRepository<DrugModel, String> {
}
