package com.TugasAkhirAPI.springapi.dto.statistics;

import com.TugasAkhirAPI.springapi.model.AppointmentModel;
import com.TugasAkhirAPI.springapi.model.User.DoctorModel;
import lombok.Getter;

import java.time.Month;
import java.time.Year;
import java.util.Arrays;
import java.util.List;

@Getter
public class DoctorSalary{
    String name;
    List<Long> amount;
    DoctorModel doctor;
    List<AppointmentModel> appointmentModels;

    public DoctorSalary(DoctorModel doctor, List<AppointmentModel> appointmentModels){
        this.doctor = doctor;
        this.name = doctor.getName();
        this.appointmentModels = appointmentModels;
    }

    public void setYearlySalary (int year){
        this.name = doctor.getName();
        Long[] amount = new Long[12];
        if(appointmentModels.size() != 0){
            for(int i=0; i<12; i++){
                amount[i] = getMonthySalary(i, year);
            }// Do it 12 times
        }
        this.amount = Arrays.asList(amount);
    }

    private long getMonthySalary(int month, int year){
        long monthlyAmount = 0L;
        for(AppointmentModel appointment: appointmentModels){
            if(!appointment.getIsDone()) continue;
            if(appointment.getStartTime().getYear() != year) continue;
            monthlyAmount += appointment.getStartTime().getMonth().getValue() == (month+1) ? doctor.getFee() : 0;
        }
        return monthlyAmount;
    }

    public void setMonthlySalary (int month, int year){
        Month current_month = Month.of(month);
        int total_day = current_month.length(Year.of(year).isLeap());
        Long[] amount = new Long[total_day];
        if(appointmentModels.size() != 0){
            for(int i=0; i< total_day; i++){
                amount[i] = getDailySalary(i, month, year);
            }// Do it total_day times
        }
        this.amount = Arrays.asList(amount);
    }
    private long getDailySalary( int day, int month, int year){
        long dailyAmount = 0L;
        for(AppointmentModel appointment: appointmentModels){
            if(!appointment.getIsDone()) continue;
            if(appointment.getStartTime().getYear() != year || appointment.getStartTime().getMonth().getValue() != month) continue;
            dailyAmount += appointment.getStartTime().getMonth().getValue() == (day+1) ? doctor.getFee() : 0;
        }
        return dailyAmount;
    }
}