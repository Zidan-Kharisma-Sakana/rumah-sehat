package com.TugasAkhirAPI.springapi.rest;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppointmentDetail {
    
    private String status;

    private String code;

    @JsonProperty("waktu-awal")
    private String waktuAwal;

    @JsonProperty("nama-dokter")
    private String namaDokter;

    @JsonProperty("nama-pasien")
    private String namaPasien;

    @JsonProperty("id-prescription")
    private Long idPrescription;
}
