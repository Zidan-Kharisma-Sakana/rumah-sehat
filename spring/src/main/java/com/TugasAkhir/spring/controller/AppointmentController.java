package com.TugasAkhir.spring.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.TugasAkhir.spring.model.AppointmentModel;
import com.TugasAkhir.spring.model.InvoiceModel;
import com.TugasAkhir.spring.service.AppointmentService;
import com.TugasAkhir.spring.service.InvoiceService;


// Notes: Please use english verb/adjective to describe your path
@Controller
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;

    @Autowired
    InvoiceService invoiceService;
    
    //@Autowired
    //UserService userService;
    
    @GetMapping(value = "/detail/{id}")
    public String detailAppointment(@PathVariable String id,Model model, Principal principal){
        AppointmentModel appointment = appointmentService.findByCode(id);
        
        SecurityContext z = SecurityContextHolder.getContext();
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) z.getAuthentication().getAuthorities();
        String role = "";
        for(GrantedAuthority i: authorities){
            role = i.getAuthority();
        }
        model.addAttribute("role", role);

        if(appointment==null ) {
            return "gagal-view-appointment";
            
        }

        model.addAttribute("appointment",appointment);
        return "detail-appointment";
    }

    @GetMapping(value = "/save/{id}")
    public String saveAppointment(@PathVariable String id, Model model, Principal principal){
        AppointmentModel appointment = appointmentService.findByCode(id);
        int status = 0;
        if(appointment==null) {
            status=1;
            model.addAttribute("status", status);
            return "gagal-save-appointment";
            
        }

        if(appointment.getPrescription()!= null){
            if(!appointment.getPrescription().getIsDone()){
                status=2;
                model.addAttribute("status", status);
                return "gagal-save-appointment";
            } else if (appointment.getIsDone()){
                status=3;
                model.addAttribute("status", status);
                return "gagal-save-appointment";
            }
        } else {
            if(appointment.getIsDone()){
                status=3;
                model.addAttribute("status", status);
                return "gagal-save-appointment";
            }
        }
        model.addAttribute("appointment", appointment);
        model.addAttribute("status", status);
        
        appointment.setIsDone(true);
        appointmentService.update(appointment);

        //Buat tagihan
        InvoiceModel invoice = new InvoiceModel();

        invoice.setDateIssued(LocalDateTime.now());
        invoice.setAppointment(appointment);
        invoice.setIsPaid(false);
        invoice.setAmount(appointment.getDoctor().getFee());

        invoiceService.add(invoice);
        

        return "save-appointment";
    }


    
}
