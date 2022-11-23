package com.TugasAkhir.spring.repository;

import com.TugasAkhir.spring.model.AppointmentModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentDB extends JpaRepository<AppointmentModel, String> {
    @Query("SELECT c FROM AppointmentModel c WHERE c.code = :code")
    Optional<AppointmentModel> findByCode(@Param("code") String code);

    
}
