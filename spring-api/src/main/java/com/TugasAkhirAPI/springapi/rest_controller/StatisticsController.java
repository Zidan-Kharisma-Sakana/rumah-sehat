package com.TugasAkhirAPI.springapi.rest_controller;

import com.TugasAkhirAPI.springapi.dto.statistics.CumulativeSalary;
import com.TugasAkhirAPI.springapi.dto.statistics.MonthlySalary;
import com.TugasAkhirAPI.springapi.dto.statistics.YearlySalary;
import com.TugasAkhirAPI.springapi.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {
    @Autowired
    StatisticService statisticService;

    @GetMapping("")
    private List<YearlySalary> getSalaryThisYear(){
        try {
            return statisticService.getThisYearSalary();
        }
        catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getLocalizedMessage()
            );
        }
    }

    @GetMapping("/monthly")
    private List<MonthlySalary> getMonthlySalary(
            @RequestParam(value = "id") List<String> values,
            @RequestParam(value = "month") String month,
            @RequestParam(value = "year") String year
    ){
        try {
            List<MonthlySalary> salary =  statisticService.getMonthlySalary(values, Integer.parseInt(month), Integer.parseInt(year));
            return salary;
        }
        catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getLocalizedMessage()
            );
        }
    }

    @GetMapping("/yearly")
    private List<YearlySalary> getYearlySalary(
            @RequestParam(value = "id") List<String> values,
            @RequestParam(value = "year") String year ){
        try {
            return statisticService.getYearlySalary(values, Integer.parseInt(year));
        }
        catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getLocalizedMessage()
            );
        }
    }

    @GetMapping("/cumulative")
    private List<CumulativeSalary> getCumulativeSalary(
            @RequestParam(value = "id") List<String> values){
        try {
            return statisticService.getCumulativeSalary(values);
        }
        catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getLocalizedMessage()
            );
        }
    }


}
