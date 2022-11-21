package com.TugasAkhirAPI.springapi.service;

import com.TugasAkhirAPI.springapi.dto.statistics.DoctorSalary;
import com.TugasAkhirAPI.springapi.model.User.DoctorModel;
import com.TugasAkhirAPI.springapi.repository.User.DoctorDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticService {
    @Autowired
    DoctorDB doctorDB;

    private List<DoctorSalary> getAllSalary(){
        List<DoctorModel> doctors = doctorDB.findAll();
        List<DoctorSalary> doctorSalaries = new ArrayList<>();
        for(DoctorModel doctor: doctors)
            doctorSalaries.add(new DoctorSalary(doctor, doctor.getListAppointment()));
        return doctorSalaries;
    }

    private List<DoctorSalary> getRelatedSalary(List<String> listId){
        List<DoctorModel> doctors = doctorDB.findAll();
        List<DoctorSalary> doctorSalaries = new ArrayList<>();
        List<DoctorModel> filteredDoctors = new ArrayList<>();
        for(DoctorModel doctor: doctors)
            if(listId.contains(doctor.getUuid()))
                filteredDoctors.add(doctor);
        for(DoctorModel doctor: filteredDoctors)
            doctorSalaries.add(new DoctorSalary(doctor, doctor.getListAppointment()));
        return doctorSalaries;
    }

    public List<DoctorSalary> getAllSalaryOnThisYear(){
        int year =LocalDate.now().getYear();
        List<DoctorSalary> relatedSalary = getAllSalary();
        for(DoctorSalary doctorSalary: relatedSalary){
            doctorSalary.setYearlySalary(year);
        }
        return relatedSalary;
    }

    public List<DoctorSalary> getSalaryOnYear(List<String> listId, int year){
        List<DoctorSalary> relatedSalary = getRelatedSalary(listId);
        for(DoctorSalary doctorSalary: relatedSalary){
            doctorSalary.setYearlySalary(year);
        }
        return relatedSalary;
    }

    public List<DoctorSalary> getSalaryOnMonthAndYear(List<String> listId, int month, int year){
        List<DoctorSalary> relatedSalary = getRelatedSalary(listId);
        for(DoctorSalary doctorSalary: relatedSalary){
            doctorSalary.setMonthlySalary(month, year);
        }
        return relatedSalary;
    }
}
