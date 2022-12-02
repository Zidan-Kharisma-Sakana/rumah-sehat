package com.TugasAkhirAPI.springapi.dto.statistics;


import com.TugasAkhirAPI.springapi.model.AppointmentModel;
import com.TugasAkhirAPI.springapi.model.User.DoctorModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class YearlySalary extends Salary {
    public YearlySalary(DoctorModel doctor, List<AppointmentModel> appointmentModels) {
        super(doctor, appointmentModels);
    }

    public YearlySalary selectAppointmentByYear(int year){
        LocalDateTime start = LocalDateTime.of(year, 1, 1, 0,0,0);
        this.start = start;
        filterAppointmentByLocalDate(start, start.plusYears(1));
        return this;
    }

    public YearlySalary calculate (){
        this.name = doctor.getName();
        LocalDateTime startOfMonth = this.start;
        Long[] amount = new Long[12];
        if(appointmentModels.size() != 0){
            for(int i=0; i<12; i++){
                amount[i] = getMonthySalary(startOfMonth);
                startOfMonth = startOfMonth.plusMonths(1);
            }
        }
        this.amount = amount;
        return this;
    }

    private long getMonthySalary(LocalDateTime startOfMonth){
        long monthlyAmount = 0L;
        for(AppointmentModel appointment: appointmentModels){
            LocalDateTime appointmentDate = appointment.getStartTime();
            LocalDateTime startOfNextMonth = startOfMonth.plusMonths(1);
            if(appointmentDate.isBefore(startOfNextMonth) && appointmentDate.isAfter(startOfMonth)){
                monthlyAmount+=doctor.getFee();
            }
        }
        return monthlyAmount;
    }

}
