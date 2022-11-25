package com.TugasAkhir.spring.repository;

import com.TugasAkhir.spring.model.DrugModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface  DrugDB extends JpaRepository<DrugModel, String> {
    @Override
    List<DrugModel> findAll();

    Optional<DrugModel> findById(String id);
}
