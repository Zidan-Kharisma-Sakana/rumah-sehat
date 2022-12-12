package com.TugasAkhirAPI.springapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.Nullable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Table(name="invoice")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceModel {
    @Id
    @GenericGenerator(name = "code", strategy = "com.TugasAkhirAPI.springapi.modelInvoiceCodeGenerator")
    @GeneratedValue(generator = "code")  
    @Column(name="code")
    private String code;

    @NotNull
    private LocalDateTime dateIssued;

    private LocalDateTime datePaid;

    @NotNull
    private Boolean isPaid;

    @NotNull
    private Long amount;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "appointment_code")
    @Nullable
    private AppointmentModel appointment;
    
}
