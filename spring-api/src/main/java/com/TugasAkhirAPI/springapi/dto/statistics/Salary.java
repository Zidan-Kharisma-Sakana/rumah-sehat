package com.TugasAkhirAPI.springapi.dto.statistics;

import com.TugasAkhirAPI.springapi.model.AppointmentModel;
import com.TugasAkhirAPI.springapi.model.User.DoctorModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Salary {
    String name;
    Long[] amount;

    LocalDateTime start;

    @JsonIgnore
    DoctorModel doctor;
    @JsonIgnore
    List<AppointmentModel> appointmentModels;

    public Salary(DoctorModel doctor, List<AppointmentModel> appointmentModels){
        this.doctor = doctor;
        this.name = doctor.getName();
        this.appointmentModels = appointmentModels;
    }

    protected void filterAppointmentByStatus(){
        List<AppointmentModel> newAppointment = new ArrayList<>();
        for(AppointmentModel appointment: appointmentModels){
            if(appointment.getIsDone()){
                newAppointment.add(appointment);
            }
        }
        this.appointmentModels = newAppointment;
    }

    protected void filterAppointmentByLocalDate(LocalDateTime start, LocalDateTime end){
        filterAppointmentByStatus();
        List<AppointmentModel> newAppointment = new ArrayList<>();
        for(AppointmentModel appointment: appointmentModels){
            LocalDateTime appointmentDate = appointment.getStartTime();
            if(appointmentDate.isBefore(end) && appointmentDate.isAfter(start)){
                newAppointment.add(appointment);
            }
        }
        this.appointmentModels = newAppointment;
    }
}
