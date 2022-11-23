package com.TugasAkhirAPI.springapi.model.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

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
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String uuid;

    @NotNull
    private String role;

    @NotNull
    @Column(unique=true)
    private String username;

    @NotNull
    private String password;

    @NotNull
    @Column(unique=true)
    private String email;
}