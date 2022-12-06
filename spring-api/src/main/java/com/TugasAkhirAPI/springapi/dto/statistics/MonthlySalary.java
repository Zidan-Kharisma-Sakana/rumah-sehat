package com.TugasAkhirAPI.springapi.dto.statistics;

import com.TugasAkhirAPI.springapi.model.AppointmentModel;
import com.TugasAkhirAPI.springapi.model.User.DoctorModel;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.List;

public class MonthlySalary extends Salary{
    public MonthlySalary(DoctorModel doctor, List<AppointmentModel> appointmentModels) {
        super(doctor, appointmentModels);
    }

    public MonthlySalary selectAppointmentByMonth(int year, int month){
        LocalDateTime start = LocalDateTime.of(year, month, 1, 0,0,0);
        this.start = start;
        filterAppointmentByLocalDate(start, start.plusMonths(1));
        return this;
    }

    public MonthlySalary calculate (){
        Month current_month = start.getMonth();
        int total_day = current_month.length(Year.of(start.getYear()).isLeap());
        Long[] amount = new Long[total_day];
        if(appointmentModels.size() != 0){
            for(int i=0; i< total_day; i++){
                long dailySalary =  getDailySalary(i);
                amount[i] = dailySalary;
            }
        }
        this.amount = amount;
        return this;
    }

    private long getDailySalary(int day){
        day++;
        long dailyAmount = 0L;
        for(AppointmentModel appointment: appointmentModels){
            if(appointment.getStartTime().getDayOfMonth() != day) continue;
            dailyAmount += doctor.getFee();
        }
        return dailyAmount;
    }
}
