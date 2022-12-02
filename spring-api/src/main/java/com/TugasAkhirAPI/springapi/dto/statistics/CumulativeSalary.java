package com.TugasAkhirAPI.springapi.dto.statistics;

import com.TugasAkhirAPI.springapi.model.AppointmentModel;
import com.TugasAkhirAPI.springapi.model.User.DoctorModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@JsonIgnoreProperties({"amount", "start"})
public class CumulativeSalary extends Salary {
    Long totalAmount = 0L;
    Long totalAppointment = 0L;

    public CumulativeSalary(DoctorModel doctor, List<AppointmentModel> appointmentModels) {
        super(doctor, appointmentModels);
        filterAppointmentByStatus();
    }

    public CumulativeSalary calculate(){
        for(AppointmentModel appointment: appointmentModels){
            this.totalAppointment++;
            this.totalAmount+= doctor.getFee();
        }
        return this;
    }

}
