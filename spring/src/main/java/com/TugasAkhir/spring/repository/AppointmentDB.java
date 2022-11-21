package com.TugasAkhir.spring.repository;

import com.TugasAkhir.spring.model.AppointmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentDB extends JpaRepository<AppointmentModel, String> {
}
