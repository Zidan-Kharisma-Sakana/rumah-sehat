package com.TugasAkhirAPI.springapi.service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.TugasAkhirAPI.springapi.dto.rayyan.AppointmentResponse;
import com.TugasAkhirAPI.springapi.dto.rayyan.CreateAppointmentRequest;
import com.TugasAkhirAPI.springapi.dto.rayyan.CreateAppointmentResponse;
import com.TugasAkhirAPI.springapi.model.User.DoctorModel;
import com.TugasAkhirAPI.springapi.model.User.PatientModel;
import com.TugasAkhirAPI.springapi.model.AppointmentModel;
import com.TugasAkhirAPI.springapi.repository.AppointmentDB;

@Service
public class AppointmentService {
    @Autowired
    AppointmentDB appointmentDB;

    public AppointmentModel update(AppointmentModel appointment){
        appointmentDB.save(appointment);
        return appointment;
    }

    public AppointmentModel findByCode(String code){
        Optional<AppointmentModel> appointment = appointmentDB.findByCode(code);
        if(!appointment.isPresent()){
            return null;
        } else return appointment.get();
    }
    public List<AppointmentResponse> returnAppointments(PatientModel patient){
        List<AppointmentModel> all = appointmentDB.findAll();
        List<AppointmentResponse> res = new ArrayList<>();
        for(AppointmentModel ap : all){
            if(ap.getPatient().getUuid().equals(patient.getUuid())){
                res.add(new AppointmentResponse(ap));
            }
        }
        return res;
    }

    public CreateAppointmentResponse createAppointment(CreateAppointmentRequest request, PatientModel patient, DoctorModel doctor) throws Exception {
        List<AppointmentModel> apts = appointmentDB.findAll();
        LocalDateTime start = request.getStartTime().minusMinutes(1);
        LocalDateTime end = start.plusHours(1);
        for(AppointmentModel appointment: apts){
            if(appointment.getStartTime().isAfter(start) && appointment.getStartTime().isBefore(end)){
                if(appointment.getDoctor().getUuid().equals(request.getDoctor_uuid())){
                    throw new Exception("Jadwal Bentrok");
                }
            }
        }
        AppointmentModel newAppointment = new AppointmentModel();
        newAppointment.setPatient(patient);
        newAppointment.setDoctor(doctor);
        newAppointment.setCode("APT-"+(apts.size()+1));
        newAppointment.setIsDone(false);
        newAppointment.setStartTime(request.getStartTime());
        newAppointment = appointmentDB.save(newAppointment);
        return new CreateAppointmentResponse().success("Sukses, Kode Appointment adalah "+ newAppointment.getCode());
    }

}
