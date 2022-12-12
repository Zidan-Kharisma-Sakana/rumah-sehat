package com.TugasAkhirAPI.springapi.dto.mobile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DrugPrescriptionDetail {
    

    int quantity;

    String nama;
    
}
