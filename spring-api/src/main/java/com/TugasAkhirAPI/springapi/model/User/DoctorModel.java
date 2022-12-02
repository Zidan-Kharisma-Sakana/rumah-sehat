package com.TugasAkhirAPI.springapi.model.User;

import com.TugasAkhirAPI.springapi.model.AppointmentModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="doctor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorModel extends BaseUser{
    @NotNull
    private String name;

    @NotNull
    private Long fee;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor")
    private List<AppointmentModel> listAppointment;
}
