package com.TugasAkhir.spring.service.User;

import com.TugasAkhir.spring.model.User.DoctorModel;
import com.TugasAkhir.spring.repository.User.DoctorDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;

@Service
public class DoctorService {
    @Autowired
    DoctorDB doctorDB;

    public List<DoctorModel> findAll() {
        return doctorDB.findAll();
    }

    public void addDoctor(DoctorModel doctor) {
        String pass = encrypt(doctor.getPassword());
        doctor.setPassword(pass);
        doctorDB.save(doctor);
    }

    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    public DoctorModel getUserByUsername(String username) {
        return doctorDB.findByUsername(username);
    }

    public void deleteUser(DoctorModel user) {
        doctorDB.delete(user);
    }

    public List<DoctorModel> getAllDoctors(){
        return doctorDB.findAll();
    }
}
