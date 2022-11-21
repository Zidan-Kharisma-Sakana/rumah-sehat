package com.TugasAkhir.spring.controller;

import com.TugasAkhir.spring.dto.auth.LoginResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// Notes: Please use english verb/adjective to describe your path
@Controller
@RequestMapping("/auth")
public class AuthController {
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping(value = "/login", params = {"admin"})
    public String loginAdmin(@ModelAttribute LoginResponse karyawan, Model model){
        return "karyawan/ubahDetailForm";
    }

    @PostMapping(value = "/login", params = {"doctor"})
    public String loginDoctor(@ModelAttribute LoginResponse karyawan, Model model){
        return "karyawan/ubahDetailForm";
    }

    @PostMapping(value = "/login", params = {"apothecary"})
    public String loginApothecary(@ModelAttribute LoginResponse karyawan, Model model){

        return "karyawan/ubahDetailForm";
    }

}
