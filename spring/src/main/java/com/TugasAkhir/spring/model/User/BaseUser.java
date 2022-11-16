package com.TugasAkhir.spring.model.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
// To Lecturer/TA: All models were written with the help of everyone

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