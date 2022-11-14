package com.TugasAkhir.spring.repository;

import com.TugasAkhir.spring.model.InvoiceModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceDB extends JpaRepository<InvoiceModel, String> {
}
