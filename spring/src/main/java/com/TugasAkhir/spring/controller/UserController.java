package com.TugasAkhir.spring.controller;

import com.TugasAkhir.spring.model.User.ApothecaryModel;
import com.TugasAkhir.spring.model.User.DoctorModel;
import com.TugasAkhir.spring.model.User.PatientModel;
import com.TugasAkhir.spring.service.User.ApothecaryService;
import com.TugasAkhir.spring.service.User.DoctorService;
import com.TugasAkhir.spring.service.User.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;

// Notes: Please use english verb/adjective to describe your path
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ApothecaryService apothecaryService;

    @Autowired
    private PatientService patientService;

    @GetMapping(value = "/doctor/all")
    private String viewDoctor(Model model) {
        List<DoctorModel> listDoctor = doctorService.findAll();
        model.addAttribute("listDoctor", listDoctor);
        return "view-doctor";
    }

    @GetMapping(value = "/doctor/add")
    private String addDoctorFormPage(Model model) {
        DoctorModel doctor = new DoctorModel();
        model.addAttribute("doctor", doctor);
        return "form-add-doctor";
    }

    @PostMapping(value = "/doctor/add")
    private String addDoctorSubmit(@ModelAttribute DoctorModel doctor) {
        doctorService.addDoctor(doctor);
        return "redirect:/user/doctor/all";
    }

    @GetMapping("/doctor/delete/{username}")
    private String deleteDoctor(@PathVariable String username) {
        DoctorModel user = doctorService.getUserByUsername(username);
        doctorService.deleteUser(user);
        return "redirect:/user/doctor/all";
    }

    @GetMapping(value = "/apothecary/all")
    private String viewApothecary(Model model) {
        List<ApothecaryModel> listApothecary = apothecaryService.findAll();
        model.addAttribute("listApothecary", listApothecary);
        return "view-apothecary";
    }

    @GetMapping(value = "/apothecary/add")
    private String addApothecary(Model model) {
        ApothecaryModel apothecary = new ApothecaryModel();
        model.addAttribute("apothecary", apothecary);
        return "form-add-apothecary";
    }

    @PostMapping(value = "/apothecary/add")
    private String addApothecarySubmit(@ModelAttribute ApothecaryModel apothecary) {
        apothecaryService.addApothecary(apothecary);
        return "redirect:/";
    }

    @GetMapping("/apothecary/delete/{username}")
    private String deleteApothecary(@PathVariable String username) {
        ApothecaryModel user = apothecaryService.getUserByUsername(username);

        apothecaryService.deleteUser(user);
        return "redirect:/";
    }

    @GetMapping(value = "/patient/all")
    private String viewPatient(Model model) {
        List<PatientModel> listPatient = patientService.findAll();
        model.addAttribute("listPatient", listPatient);
        return "view-patient";
    }
}
