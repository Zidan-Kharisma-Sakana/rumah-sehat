package com.TugasAkhirAPI.springapi.rest_controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.TugasAkhirAPI.springapi.dto.mobile.PrescriptionMobile;
import com.TugasAkhirAPI.springapi.dto.mobile.AppointmentDetail;
import com.TugasAkhirAPI.springapi.dto.mobile.DrugPrescriptionDetail;
import com.TugasAkhirAPI.springapi.model.PrescriptionModel;
import com.TugasAkhirAPI.springapi.service.PrescriptionService;

// Notes: Please use english verb/adjective to describe your path
@RestController
@RequestMapping("/api/prescription")
public class PrescriptionRESTController {
    @Autowired
    PrescriptionService prescriptionService;

    @GetMapping(value="/detail/{id}")
    private PrescriptionMobile detailAppointment(@PathVariable("id") String id){
        try{
            
            PrescriptionMobile pres = prescriptionService.findById(Long.parseLong(id));
            System.out.println(pres.getNamaConfirmer());
            System.out.println(pres.getNamaPasien());
            System.out.println(pres.getNamaDokter());
            System.out.println(pres.getListDrug());
            
            System.out.println(pres.getStatus());
            System.out.println(pres.getWaktuAwal());


            return prescriptionService.findById(Long.parseLong(id));
            
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Prescription" + id + " not found");
        }
        
    }

    @GetMapping(value="/mock")
    private PrescriptionMobile mockDetailAppointment(){
        try{
            PrescriptionMobile result = new PrescriptionMobile();
            List<DrugPrescriptionDetail> drugDetails = new ArrayList<>();
            for(int i=1; i<6; i++){
                DrugPrescriptionDetail newDrug = new DrugPrescriptionDetail();
                newDrug.setNama("Obat ke "+i);
                newDrug.setQuantity(6-i);
                drugDetails.add(newDrug);
            }
            result.setCode(123L);
            result.setStatus("Selesai");
            result.setNamaPasien("Dormamu");
            result.setNamaDokter("Dokter Strange");
            result.setWaktuAwal(LocalDateTime.now().toString());
            result.setNamaConfirmer("apoteker X");
            result.setListDrug(drugDetails);
            return result;
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Prescription not found");
        }

    }

    @GetMapping(value="/detail-drugs/{id}")
    private List<DrugPrescriptionDetail> detailDrugPrescription(@PathVariable("id") String id){
        try{
            
            PrescriptionMobile pres = prescriptionService.findById(Long.parseLong(id));


            return pres.getListDrug();
            
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Prescription" + id + " not found");
        }
        
    }
}
