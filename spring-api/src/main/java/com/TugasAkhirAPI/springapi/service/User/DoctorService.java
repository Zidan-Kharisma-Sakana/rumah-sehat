package com.TugasAkhirAPI.springapi.service.User;

import com.TugasAkhirAPI.springapi.model.User.DoctorModel;
import com.TugasAkhirAPI.springapi.repository.User.DoctorDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DoctorService {
    @Autowired
    DoctorDB doctorDB;
    public DoctorModel findByUuid(String id){
        return doctorDB.findById(id).orElse(null);
    }

    public List<DoctorModel> findAll(){
        return doctorDB.findAll();
    }

}
