package com.TugasAkhirAPI.springapi.model;

import com.TugasAkhirAPI.springapi.utils.CustomGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.annotation.Nullable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="invoice")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceModel {
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bill-generator")
//    @GenericGenerator(name = "bill-generator",
//            parameters = {
//                    @Parameter(name = CustomGenerator.INCREMENT_PARAM, value = "1"),
//                    @Parameter(name = CustomGenerator.VALUE_PREFIX_PARAMETER, value = "BILL-"),
//                    @Parameter(name = CustomGenerator.NUMBER_FORMAT_PARAMETER, value = "%d")
//            },
//            strategy = "com.TugasAkhirAPI.springapi.utils.CustomGenerator")
    private String code;

    @NotNull
    private LocalDateTime dateIssued;

    private LocalDateTime datePaid;

    @NotNull
    private Boolean isPaid;

    @NotNull
    private Long amount;

    // TODO : Delete the Nullable Annot
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "appointment_code")
    @Nullable
    private AppointmentModel appointment;
}
