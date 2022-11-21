package com.TugasAkhir.spring.model.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
// To Lecturer/TA: All models were written with the help of everyone

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties(value = {"password"}, allowSetters = true)
public class BaseUser {
    @Id
    private String uuid;

    @NotNull
    private String name;

    @NotNull
    @Column(unique=true)
    private String username;

    @NotNull
    private String password;

    @NotNull
    @Column(unique=true)
    private String email;
}