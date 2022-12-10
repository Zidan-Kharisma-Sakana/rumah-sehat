package com.TugasAkhirAPI.springapi.dto.mobile;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PrescriptionMobile {
    private Long code;

    private String status;

    @JsonProperty("waktu-awal")
    private String waktuAwal;

    @JsonProperty("nama-dokter")
    private String namaDokter;

    @JsonProperty("nama-pasien")
    private String namaPasien;

    @JsonProperty("nama-confirmer")
    private String namaConfirmer;


    @JsonProperty("list-drug")
    private List<DrugPrescriptionDetail> listDrug;
}
