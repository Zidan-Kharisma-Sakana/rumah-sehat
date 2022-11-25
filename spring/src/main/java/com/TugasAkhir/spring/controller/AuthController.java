package com.TugasAkhir.spring.controller;

import com.TugasAkhir.spring.config.SSOConfig;
import com.TugasAkhir.spring.model.User.BaseUser;
import com.TugasAkhir.spring.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

// Notes: Please use english verb/adjective to describe your path
@CrossOrigin()
@Controller
public class AuthController {
    @Autowired
    ServerProperties serverProperties;
    private WebClient webClient = WebClient.builder().build();
    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/validate-ticket")
    public ModelAndView adminLoginSSO(
            @RequestParam(value = "ticket", required = false) String ticket,
            HttpServletRequest request
    ){
        userService.adminLoginSSO(ticket, request, this.webClient);
        return new ModelAndView("redirect:/");
    }
    @GetMapping(value = "/login-sso")
    public ModelAndView loginSSO(){
        return new ModelAndView("redirect:"+SSOConfig.SERVER_LOGIN+SSOConfig.CLIENT_LOGIN);
    }
    @GetMapping(value = "/logout-sso")
    public ModelAndView logoutSSO(Principal principal){
        BaseUser user = userService.findByUsername(principal.getName());
        if(!user.getRole().equals("ADMIN")){
            return new ModelAndView("redirect:/logout");
        }
        return new ModelAndView("redirect:"+SSOConfig.SERVER_LOGOUT+SSOConfig.CLIENT_LOGOUT);
    }
}
