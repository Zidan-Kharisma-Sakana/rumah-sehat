package com.TugasAkhirAPI.springapi.service;

import com.TugasAkhirAPI.springapi.dto.mobile.PrescriptionMobile;
import com.TugasAkhirAPI.springapi.model.PrescriptionModel;
import com.TugasAkhirAPI.springapi.repository.PrescriptionDB;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PrescriptionService {
    @Autowired
    PrescriptionDB prescriptionDB;

    @Autowired
    DrugPrescriptionService drugPrescriptionService;

    public PrescriptionMobile findById(Long id){
        Optional<PrescriptionModel> prescription = prescriptionDB.findById(id);

        if(prescription.isPresent()){
            PrescriptionModel prescriptionGet = prescription.get();
            PrescriptionMobile prescriptionJson = new PrescriptionMobile();
            prescriptionJson.setCode(prescriptionGet.getId());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");

            prescriptionJson.setCode(id);
            prescriptionJson.setWaktuAwal(prescriptionGet.getCreatedAt().format(formatter));
            prescriptionJson.setNamaDokter(prescriptionGet.getAppointment().getDoctor().getName());
            prescriptionJson.setNamaPasien(prescriptionGet.getAppointment().getPatient().getName());
            prescriptionJson.setListDrug(drugPrescriptionService.getListOfDrugdByPrescriptionId(id));
            if(prescriptionGet.getIsDone()){
                prescriptionJson.setStatus("Selesai");

            }else{

                prescriptionJson.setStatus("Belum Selesai");
            }

            if(prescriptionGet.getConfirmer() == null){
                prescriptionJson.setNamaConfirmer("-");
            }else{
                prescriptionJson.setNamaConfirmer(prescriptionGet.getConfirmer().getName());
            }
            return prescriptionJson;
        }   return null;
    
}

}
