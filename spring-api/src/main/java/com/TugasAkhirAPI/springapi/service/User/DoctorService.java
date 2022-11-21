package com.TugasAkhirAPI.springapi.service.User;

import com.TugasAkhirAPI.springapi.repository.User.DoctorDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    @Autowired
    DoctorDB doctorDB;
}
