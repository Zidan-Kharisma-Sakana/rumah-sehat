package com.TugasAkhir.spring.controller;

import com.TugasAkhir.spring.model.User.BaseUser;
import com.TugasAkhir.spring.service.StatisticService;
import com.TugasAkhir.spring.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Collection;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String Home(Model model){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Collection<GrantedAuthority> authorities =
                (Collection<GrantedAuthority>) securityContext.getAuthentication().getAuthorities();
        String role = "";
        for (GrantedAuthority authority : authorities) {
            role = authority.getAuthority();
        }
        model.addAttribute("role", role);
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
