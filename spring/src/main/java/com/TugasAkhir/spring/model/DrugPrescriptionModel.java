package com.TugasAkhir.spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="drug_prescription")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DrugPrescriptionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "drug_id_obat")
    private DrugModel drug;
    
    @NotNull
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "prescription_id")
    private PrescriptionModel prescription;

}
