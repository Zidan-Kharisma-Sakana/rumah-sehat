package com.TugasAkhir.spring.model.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@JsonIgnoreProperties(value = {"password"}, allowSetters = true)
public class BaseUser {
    // To Lecturer/TA/Friends :
    // I do not include uuid here because spring requires every model need at
    // least 1 more attributes from its parent. In the table models,
    // AdminModel had none so I couldn't put uuid here
    @NotNull
    private String name;

    @NotNull
    private String role;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String email;
}