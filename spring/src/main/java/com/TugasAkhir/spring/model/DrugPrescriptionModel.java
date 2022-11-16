package com.TugasAkhir.spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "prescription_id")
    private PrescriptionModel prescription;

}
