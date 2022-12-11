package com.TugasAkhirAPI.springapi.rest_controller;

import com.TugasAkhirAPI.springapi.dto.statistics.CumulativeSalary;
import com.TugasAkhirAPI.springapi.dto.statistics.MonthlySalary;
import com.TugasAkhirAPI.springapi.dto.statistics.YearlySalary;
import com.TugasAkhirAPI.springapi.service.StatisticService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {
    @Autowired
    StatisticService statisticService;

    @GetMapping("")
    private List<YearlySalary> getSalaryThisYear(){
        try {
            log.info("Try to get statistics this year");
            return statisticService.getThisYearSalary();
        }
        catch (Exception e){
            log.warn("Not found any statistic");
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
            log.info("Try to get salary");
            List<MonthlySalary> salary =  statisticService.getMonthlySalary(values, Integer.parseInt(month), Integer.parseInt(year));
            if (salary != null){
                log.info("Salary founded");
            }
            return salary;
        }
        catch (Exception e){
            log.warn("Not found any salary");
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
            log.info("Try to get yearly salary");
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
            log.info("Try to get cumulative salary");
            return statisticService.getCumulativeSalary(values);
        }
        catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getLocalizedMessage()
            );
        }
    }


}
