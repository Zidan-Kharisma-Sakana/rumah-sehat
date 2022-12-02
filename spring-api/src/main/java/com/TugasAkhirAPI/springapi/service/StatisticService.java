package com.TugasAkhirAPI.springapi.service;

import com.TugasAkhirAPI.springapi.dto.statistics.CumulativeSalary;
import com.TugasAkhirAPI.springapi.dto.statistics.MonthlySalary;
import com.TugasAkhirAPI.springapi.dto.statistics.Salary;
import com.TugasAkhirAPI.springapi.dto.statistics.YearlySalary;
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

    private List<Salary> getAllSalary(){
        List<DoctorModel> doctors = doctorDB.findAll();
        List<Salary> doctorSalaries = new ArrayList<>();
        for(DoctorModel doctor: doctors)
            doctorSalaries.add(new Salary(doctor, doctor.getListAppointment()));
        return doctorSalaries;
    }

    private List<Salary> getRelatedSalary(List<String> listId){
        List<DoctorModel> doctors = doctorDB.findAll();
        List<Salary> doctorSalaries = new ArrayList<>();
        List<DoctorModel> filteredDoctors = new ArrayList<>();
        for(DoctorModel doctor: doctors)
            if(listId.contains(doctor.getUuid()))
                filteredDoctors.add(doctor);
        for(DoctorModel doctor: filteredDoctors) doctorSalaries.add(new Salary(doctor, doctor.getListAppointment()));
        return doctorSalaries;
    }

    public List<YearlySalary> getThisYearSalary(){
        int year =LocalDate.now().getYear();
        List<Salary> relatedSalary = getAllSalary();

        List<YearlySalary> yearlySalaries = new ArrayList<>();

        for(Salary salary: relatedSalary){
            YearlySalary yearlySalary =
                    new YearlySalary(salary.getDoctor(), salary.getAppointmentModels())
                            .selectAppointmentByYear(year)
                            .calculate();
            yearlySalaries.add(yearlySalary);
        }
        return yearlySalaries;
    }

    public List<YearlySalary> getYearlySalary(List<String> listId, int year){
        List<Salary> relatedSalary = getRelatedSalary(listId);
        List<YearlySalary> yearlySalaries = new ArrayList<>();
        for(Salary salary: relatedSalary){
            YearlySalary yearlySalary =
                    new YearlySalary(salary.getDoctor(), salary.getAppointmentModels())
                            .selectAppointmentByYear(year)
                            .calculate();
            yearlySalaries.add(yearlySalary);
        }
        return yearlySalaries;
    }

    public List<MonthlySalary> getMonthlySalary(List<String> listId, int month, int year){
        List<Salary> relatedSalary = getRelatedSalary(listId);
        List<MonthlySalary> monthlySalaries = new ArrayList<>();
        for(Salary salary: relatedSalary){
            MonthlySalary monthlySalary =
                    new MonthlySalary(salary.getDoctor(), salary.getAppointmentModels())
                            .selectAppointmentByMonth(year, month)
                            .calculate();
            monthlySalaries.add(monthlySalary);
        }
        return monthlySalaries;
    }

    public List<CumulativeSalary> getCumulativeSalary(List<String> listId){
        List<Salary> relatedSalary = getRelatedSalary(listId);
        List<CumulativeSalary> cumulativeSalaries = new ArrayList<>();
        for(Salary salary: relatedSalary){
            CumulativeSalary cumulativeSalary =
                    new CumulativeSalary(salary.getDoctor(), salary.getAppointmentModels())
                            .calculate();
            cumulativeSalaries.add(cumulativeSalary);
        }
        return cumulativeSalaries;
    }
}
