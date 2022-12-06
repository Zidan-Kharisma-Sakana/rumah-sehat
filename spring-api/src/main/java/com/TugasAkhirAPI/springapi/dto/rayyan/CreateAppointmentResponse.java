package com.TugasAkhirAPI.springapi.dto.rayyan;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAppointmentResponse {
    Boolean success;
    String message;

    public CreateAppointmentResponse fail(String message){
        this.success = false;
        this.message = message;
        return this;
    }
    public CreateAppointmentResponse success(String message){
        this.success = true;
        this.message = message;
        return this;
    }
}