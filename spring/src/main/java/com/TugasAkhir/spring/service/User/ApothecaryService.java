package com.TugasAkhir.spring.service.User;

import com.TugasAkhir.spring.model.User.ApothecaryModel;
import com.TugasAkhir.spring.repository.AppointmentDB;
import com.TugasAkhir.spring.repository.User.ApothecaryDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApothecaryService {
    @Autowired
    AppointmentDB appointmentDB;
    @Autowired
    ApothecaryDB apothecaryDB;

    public List<ApothecaryModel> findAll() {
        return apothecaryDB.findAll();
    }

    public void addApothecary(ApothecaryModel apothecary) {
        String pass = encrypt(apothecary.getPassword());
        apothecary.setPassword(pass);
        apothecaryDB.save(apothecary);
    }

    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    public ApothecaryModel getUserByUsername(String username) {
        return apothecaryDB.findByUsername(username);
    }

    public void deleteUser(ApothecaryModel user) {
        apothecaryDB.delete(user);
    }
}
