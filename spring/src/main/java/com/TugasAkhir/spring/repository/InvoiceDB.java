package com.TugasAkhir.spring.repository;

import com.TugasAkhir.spring.model.AppointmentModel;
import com.TugasAkhir.spring.model.InvoiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceDB extends JpaRepository<InvoiceModel, String> {
    @Query("SELECT tm FROM InvoiceModel tm JOIN AppointmentModel am ON tm.appointment.code = am.code WHERE am.patient.uuid = :pasien")

    List<InvoiceModel> findTagihanOfPasien(@Param("pasien") String pasien);

    Optional<InvoiceModel> findTagihanModelByAppointment(AppointmentModel appointment);
}
