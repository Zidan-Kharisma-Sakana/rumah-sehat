package com.TugasAkhirAPI.springapi.dto.rayyan;

import com.TugasAkhirAPI.springapi.model.AppointmentModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentResponse {
    private String code;
    private String doctor_name;
    private String isDone;

    private String date;
    private String start;
    private String end;
    public AppointmentResponse(AppointmentModel appointmentModel){
        this.code = appointmentModel.getCode();
        this.doctor_name = appointmentModel.getDoctor().getName();
        this.isDone = appointmentModel.getIsDone() ? "Selesai": "Belum selesai";
        this.start = appointmentModel.getStartTime().toLocalTime().toString();
        this.end = appointmentModel.getStartTime().toLocalTime().plusHours(1).toString();
        this.date = appointmentModel.getStartTime().toLocalDate().toString();
    }
}
