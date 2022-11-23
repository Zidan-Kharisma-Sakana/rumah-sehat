package com.TugasAkhirAPI.springapi.repository;

import com.TugasAkhirAPI.springapi.model.InvoiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceDB extends JpaRepository<InvoiceModel, String> {
}
