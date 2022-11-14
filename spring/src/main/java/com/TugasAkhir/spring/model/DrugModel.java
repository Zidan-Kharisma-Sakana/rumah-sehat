package com.TugasAkhir.spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
}
