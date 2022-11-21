package com.TugasAkhir.spring.service.User;

import com.TugasAkhir.spring.config.SSOConfig;
import com.TugasAkhir.spring.model.User.*;
import com.TugasAkhir.spring.repository.User.*;
import com.TugasAkhir.spring.security.xml.Attributes;
import com.TugasAkhir.spring.security.xml.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    PatientDB patientDB;
    @Autowired
    DoctorDB doctorDB;
    @Autowired
    AdminDB adminDB;
    @Autowired
    ApothecaryDB apothecaryDB;

    @Autowired
    UserDB userDB;

    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    public void adminLoginSSO(String ticket, HttpServletRequest request, WebClient webClient){
        ServiceResponse serviceResponse = webClient.get().uri(
                String.format(
                        SSOConfig.SERVER_VALIDATE_TICKET,
                        ticket,
                        SSOConfig.CLIENT_LOGIN
                )
        ).retrieve().bodyToMono(ServiceResponse.class).block();
        Attributes attributes = serviceResponse.getAuthenticationSuccess().getAttributes();
        String username = serviceResponse.getAuthenticationSuccess().getUser();
        BaseUser user = userDB.findByUsername(username);
        if(user == null){
            AdminModel admin = new AdminModel();
            admin.setUsername(username);
            admin.setName(username);
            admin.setRole("ADMIN");
            admin.setEmail(username+"@ui.ac.id");
            admin.setPassword(encrypt("rumahsehat"));
            adminDB.save(admin);
        }else{
            if(!user.getRole().equals("ADMIN")){
                throw new Error("Unauthorized");
            }
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, "rumahsehat");

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        HttpSession httpSession = request.getSession(true);
        httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);
    }

    public BaseUser findByUsername(String username){
        return userDB.findByUsername(username);
    }
}
