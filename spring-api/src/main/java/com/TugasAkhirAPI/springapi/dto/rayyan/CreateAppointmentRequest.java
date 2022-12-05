package com.TugasAkhirAPI.springapi.dto.rayyan;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class CreateAppointmentRequest {
    LocalDate date;
    Integer hour;
    Integer minute;
    String doctor_uuid;

    public LocalDateTime getStartTime(){
        System.out.println(date);
        System.out.println(hour);
        System.out.println(minute);
        return LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), hour, minute, 0);
    }
}
