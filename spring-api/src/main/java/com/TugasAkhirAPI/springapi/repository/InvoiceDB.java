package com.TugasAkhirAPI.springapi.repository;

import com.TugasAkhirAPI.springapi.model.AppointmentModel;
import com.TugasAkhirAPI.springapi.model.InvoiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceDB extends JpaRepository<InvoiceModel, String> {
    InvoiceModel findByCode(String code);
    List<Optional<InvoiceModel>> findAllByAppointment_Patient_Username(String username);
}
