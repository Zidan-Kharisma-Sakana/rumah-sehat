package com.TugasAkhir.spring.model.User;

import com.TugasAkhir.spring.model.AppointmentModel;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="patient")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientModel extends BaseUser{
    @NotNull
    private String role;

    @NotNull
    private Long balance;

    @NotNull
    private Long age;

    @OneToMany(mappedBy = "patient")
    private List<AppointmentModel> listAppointment;

}
