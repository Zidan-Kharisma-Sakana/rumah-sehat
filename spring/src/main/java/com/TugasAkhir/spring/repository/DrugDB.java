package com.TugasAkhir.spring.repository;

import com.TugasAkhir.spring.model.DrugModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  DrugDB extends JpaRepository<DrugModel, String> {
}
