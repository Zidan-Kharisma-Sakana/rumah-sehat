package com.TugasAkhir.spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="drug")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DrugModel {
    @Id
    @Column(name = "id_obat")
    private String id;

    @NotNull
    @Column(name = "nama_obat")
    private String name;

    @NotNull
    @Column(name = "stok")
    private Long stock;

    @NotNull
    @Column(name = "price")
    private Long price;

    @OneToMany(mappedBy = "drug")
    private List<DrugPrescriptionModel> listPrescribedTo;
}
