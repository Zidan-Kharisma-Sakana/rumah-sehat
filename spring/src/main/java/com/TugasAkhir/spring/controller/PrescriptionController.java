package com.TugasAkhir.spring.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TugasAkhir.spring.model.AppointmentModel;
import com.TugasAkhir.spring.model.DrugModel;
import com.TugasAkhir.spring.model.DrugPrescriptionModel;
import com.TugasAkhir.spring.model.PrescriptionModel;
import com.TugasAkhir.spring.rest_controller.AppointmentRESTController;
import com.TugasAkhir.spring.service.AppointmentService;
import com.TugasAkhir.spring.service.DrugService;
import com.TugasAkhir.spring.service.PrescriptionService;

// Notes: Please use english verb/adjective to describe your path
@Controller
@RequestMapping("/prescription")
public class PrescriptionController {
    @Autowired
    PrescriptionService prescriptionService;

    @Autowired
    DrugService drugService;

    @Autowired
    AppointmentService appointmentService;

    @GetMapping(value = "{id}/add")
    public String addPerscription(@PathVariable String id,@ModelAttribute PrescriptionModel prescription,Model model, Principal principal){
        PrescriptionModel newPrescription = new PrescriptionModel();
        List<DrugPrescriptionModel> newList = new ArrayList<>();
        newPrescription.setListPrescribe(newList );
        newPrescription.getListPrescribe().add(new DrugPrescriptionModel());

        List<DrugModel> listDrugExisting = drugService.viewAll();


        model.addAttribute("listDrugExisting", listDrugExisting);
        model.addAttribute("prescription", newPrescription);
        model.addAttribute("principal",principal);
        return "form-add-prescription";
    }

    @GetMapping(value = "{id}/add" ,params="addRow")
    public String addPerscriptionRow(@PathVariable String id,@ModelAttribute PrescriptionModel prescription,Model model, Principal principal){
        PrescriptionModel newPrescription = new PrescriptionModel();
        List<DrugModel> listDrugExisting = drugService.viewAll();

        if(prescription.getListPrescribe() == null || prescription.getListPrescribe().size() ==0){
            prescription.setListPrescribe(new ArrayList<>());
        }
        newPrescription.getListPrescribe().add(new DrugPrescriptionModel());


        model.addAttribute("listDrugExisting", listDrugExisting);
        model.addAttribute("prescription", prescription);
        model.addAttribute("principal",principal);
        return "form-add-prescription";
    }

    @GetMapping(value = "{id}/add" ,params="deleteRow")
    public String deletePerscriptionRow(@PathVariable String id,@RequestParam("deleteRow") Integer row,@ModelAttribute PrescriptionModel prescription,Model model, Principal principal){
        
        List<DrugModel> listDrugExisting = drugService.viewAll();

        final Integer rowId = Integer.valueOf(row);
        prescription.getListPrescribe().remove(rowId.intValue());




        model.addAttribute("listDrugExisting", listDrugExisting);
        model.addAttribute("prescription",prescription);
        model.addAttribute("principal",principal);
        return "form-add-prescription";
    }

    @PostMapping(value = "{id}/add",params="save")
    public String addPerscriptionSave(@PathVariable String id,@ModelAttribute PrescriptionModel prescription,Model model, Principal principal){
        AppointmentModel appointment = appointmentService.findById(id);

        
        prescriptionService.add(prescription);
        appointment.setPrescription(prescription);
        appointmentService.update(appointment);

        model.addAttribute("prescription", prescription);
        model.addAttribute("principal",principal);
        return "add-prescription";
    }
}
