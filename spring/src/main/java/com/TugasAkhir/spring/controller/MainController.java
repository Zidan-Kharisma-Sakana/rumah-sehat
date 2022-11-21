package com.TugasAkhir.spring.controller;

import com.TugasAkhir.spring.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class MainController {
    @GetMapping("/")
    public String Home(Model model){
        return "home";
    }

    @GetMapping("/statistics")
    public String getStatisticPage(){
        return "statistics";
    }

    @GetMapping("/admin")
    public ResponseEntity<?> admin(Principal p, Model model){
        return ResponseEntity.ok(p.getName());
    } // Contoh!

    @GetMapping("/admin_doctor")
    public ResponseEntity<?> adminAndDoctor(Principal p, Model model){
        return ResponseEntity.ok(p.getName());
    } // Contoh!
}
