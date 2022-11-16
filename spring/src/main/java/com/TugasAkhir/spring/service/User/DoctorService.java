package com.TugasAkhir.spring.service.User;

import com.TugasAkhir.spring.repository.User.DoctorDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    @Autowired
    DoctorDB doctorDB;
}
