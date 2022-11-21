package com.TugasAkhirAPI.springapi.rest_controller;

import com.TugasAkhirAPI.springapi.dto.statistics.DoctorSalary;
import com.TugasAkhirAPI.springapi.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {
    @Autowired
    StatisticService statisticService;

    @GetMapping("/monthly")
    private List<DoctorSalary> getSalaryMonth(
            @RequestParam(value = "id") List<String> values,
            @RequestParam(value = "month") String month,
            @RequestParam(value = "year") String year
    ){
        try {
            return statisticService.getSalaryOnMonthAndYear(values, Integer.parseInt(month), Integer.parseInt(year));
        }
        catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getLocalizedMessage()
            );
        }
    }

    @GetMapping("/yearly")
    private List<DoctorSalary> getSalaryYear(
            @RequestParam(value = "id") List<String> values,
            @RequestParam(value = "year") String year ){
        try {
            return statisticService.getSalaryOnYear(values, Integer.parseInt(year));
        }
        catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getLocalizedMessage()
            );
        }

    }
}
