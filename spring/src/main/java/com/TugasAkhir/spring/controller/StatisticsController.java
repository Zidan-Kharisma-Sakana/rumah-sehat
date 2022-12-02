package com.TugasAkhir.spring.controller;

import com.TugasAkhir.spring.model.User.DoctorModel;
import com.TugasAkhir.spring.service.User.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {
    @Autowired
    DoctorService doctorService;
    @GetMapping(value = "")
    private String getStatistics(Model model) {
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "statistics/all";
    }

    @GetMapping(value = "/monthly")
    private String getMnthly(Model model) {
        return "statistics/monthly";
    }

    @GetMapping(value = "/yearly")
    private String getYearly(Model model) {
        return "statistics/yearly";
    }

    @GetMapping(value = "/cumulative")
    private String getCumulative(Model model) {
        return "statistics/cumulative";
    }
}
