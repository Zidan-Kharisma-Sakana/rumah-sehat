package com.TugasAkhirAPI.springapi.model;

import com.TugasAkhirAPI.springapi.model.User.DoctorModel;
import com.TugasAkhirAPI.springapi.model.User.PatientModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.Nullable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name="appointment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentModel {
    @Id
    private String code;

    @NotNull
    private LocalDateTime startTime;

    @NotNull
    private Boolean isDone;

    @ManyToOne
    @JoinColumn(name = "patient_uuid")
    @NotNull
    private PatientModel patient;

    @ManyToOne
    @JoinColumn(name = "doctor_uuid")
    @NotNull
    private DoctorModel doctor;

    @OneToOne
    @JoinColumn(name = "invoice_code")
    private InvoiceModel invoice;

    // TODO : delete Nullable annot
    @OneToOne
    @JoinColumn(name = "prescription_code")
    @Nullable
    private PrescriptionModel prescription;
}
