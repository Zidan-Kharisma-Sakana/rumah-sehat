package com.TugasAkhir.spring.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.notification.NotificationPublisherAware;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.TugasAkhir.spring.model.InvoiceModel;
import com.TugasAkhir.spring.model.PrescriptionModel;
import com.TugasAkhir.spring.model.User.ApothecaryModel;
import com.TugasAkhir.spring.repository.DrugDB;
import com.TugasAkhir.spring.service.AppointmentService;
import com.TugasAkhir.spring.service.DrugPrescriptionService;
import com.TugasAkhir.spring.service.DrugService;
import com.TugasAkhir.spring.service.InvoiceService;
import com.TugasAkhir.spring.service.PrescriptionService;
import com.TugasAkhir.spring.service.User.ApothecaryService;

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

    @Autowired
    DrugPrescriptionService drugPrescriptionService;

    @Autowired
    ApothecaryService apothecaryService;

    @Autowired
    InvoiceService invoiceService;

    @GetMapping(value = "/add/{code}")
    public String addPerscription(@PathVariable String code,Principal principal,Model model){

        PrescriptionModel newPrescription = new PrescriptionModel();
        AppointmentModel appointment = appointmentService.findByCode(code);

        SecurityContext z = SecurityContextHolder.getContext();
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) z.getAuthentication().getAuthorities();
        String role = "";
        for(GrantedAuthority i: authorities){
            role = i.getAuthority();
        }
        model.addAttribute("role", role);
        
        if(appointment == null  || appointment.getIsDone() || appointment.getPrescription()!=null){
            return "gagal-add-prescription";
        }
        
        newPrescription.setAppointment(appointment);
        newPrescription.setCreatedAt(LocalDateTime.now());
        newPrescription.setIsDone(false);

        List<DrugPrescriptionModel> newList = new ArrayList<>();
        newPrescription.setListPrescribe(newList );

        DrugPrescriptionModel drugPrescription = new DrugPrescriptionModel();
        drugPrescription.setPrescription(newPrescription);
        newPrescription.getListPrescribe().add(drugPrescription);

        List<DrugModel> listDrugExisting = drugService.viewAll();


        model.addAttribute("listDrugExisting", listDrugExisting);
        model.addAttribute("prescription", newPrescription);
        model.addAttribute("principal",principal);
        model.addAttribute("code", code);
        return "form-add-prescription";
    }

    @PostMapping(value = "/add/{code}" ,params="addRow")
    public String addPerscriptionRow(@PathVariable String code,@ModelAttribute PrescriptionModel prescription,Model model, Principal principal){
        
        List<DrugModel> listDrugExisting = drugService.viewAll();

        if(prescription.getListPrescribe() == null || prescription.getListPrescribe().size() ==0){
            prescription.setListPrescribe(new ArrayList<>());
        }
        DrugPrescriptionModel drugPrescription = new DrugPrescriptionModel();
        drugPrescription.setPrescription(prescription);
        prescription.getListPrescribe().add(drugPrescription);


        model.addAttribute("listDrugExisting", listDrugExisting);
        model.addAttribute("prescription", prescription);
        model.addAttribute("principal",principal);
        model.addAttribute("code",code);
        return "form-add-prescription";
    }

    @PostMapping(value = "/add/{code}" ,params="deleteRow")
    public String deletePerscriptionRow(@PathVariable String code,@RequestParam("deleteRow") Integer row,@ModelAttribute PrescriptionModel prescription,Model model, Principal principal){
        
        List<DrugModel> listDrugExisting = drugService.viewAll();

        final Integer rowId = Integer.valueOf(row);
        prescription.getListPrescribe().remove(rowId.intValue());




        model.addAttribute("listDrugExisting", listDrugExisting);
        model.addAttribute("prescription",prescription);
        model.addAttribute("principal",principal);
        model.addAttribute("code",code);
        return "form-add-prescription";
    }

    @PostMapping(value = "/add/{code}",params="save")
    public String addPerscriptionSave(@PathVariable String code,@ModelAttribute PrescriptionModel prescription,Model model, Principal principal){
        AppointmentModel appointment = appointmentService.findByCode(code);
        List<DrugPrescriptionModel> listPrescribed = prescription.getListPrescribe();

        prescription.setIsDone(false);
        prescription.setAppointment(appointment);
        prescription.setCreatedAt(LocalDateTime.now());
        prescription.setListPrescribe(new ArrayList<>()); 
        prescriptionService.add(prescription);

        for(DrugPrescriptionModel drugPrescription : listPrescribed){
            drugPrescription.setPrescription(prescription);
            drugPrescriptionService.add(drugPrescription);
        }

        PrescriptionModel prescriptionAdded = prescriptionService.findById(prescription.getId());
        prescriptionAdded.setListPrescribe(listPrescribed);
        prescriptionService.update(prescriptionAdded);
        

        for(DrugPrescriptionModel drugPrescription : listPrescribed){
            drugPrescription.setPrescription(prescription);
            drugPrescriptionService.add(drugPrescription);
        }


        appointment.setPrescription(prescription);
        appointmentService.update(appointment);

        model.addAttribute("prescription", prescription);
        model.addAttribute("principal",principal);
        model.addAttribute("code",code);
        
        return "add-prescription";
    }

    @GetMapping("/view-all")
    public String viewAllPrescription(Model model, Principal principal){
       
        //get all prescription
        List<PrescriptionModel> listPrescription = prescriptionService.findAll();

        model.addAttribute("listPrescription", listPrescription);
        
        
        //models
        return "viewall-prescription";

    }

    @GetMapping("/detail/{id}")
    public String detailPrescription(@PathVariable String id,Model model, Principal principal){
        //get prescription
        PrescriptionModel prescription =prescriptionService.findById(Long.parseLong(id));
        List<DrugPrescriptionModel> listOfDrugs = drugPrescriptionService.getListOfDrugdByPrescriptionId(Long.parseLong(id));
        // get role
        SecurityContext z = SecurityContextHolder.getContext();
        //check stock
        boolean isStockEnough = true;
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) z.getAuthentication().getAuthorities();
        String role = "";
        for(GrantedAuthority i: authorities){
            role = i.getAuthority();
        }
        // check if prescription exists
        if(prescription== null){
            return "gagal-view-prescription";
        }
        // check stock
        for (DrugPrescriptionModel drugPrescription : prescription.getListPrescribe()){
            Long stock = drugService.getDrug(drugPrescription.getDrug().getId()).getStock();
            if(stock< drugPrescription.getQuantity()){
                isStockEnough = false;
                break;
            }
        }
        
        //models
        model.addAttribute("isStockEnough", isStockEnough);
        //models
        model.addAttribute("role", role);
        model.addAttribute("prescription", prescription);
        model.addAttribute("listDrugs", listOfDrugs);

        
        return "detail-prescription";

    }

    @GetMapping("/save/{id}")
    public String savePrescription(@RequestParam String id,Model model, Principal principal){

        ApothecaryModel apoteker = apothecaryService.getUserByUsername(principal.getName());
        
        
        // get prescription
        PrescriptionModel prescription = prescriptionService.findById(Long.parseLong(id));
        // check if prescription exist
        //check if presctiption is done
        //check if appointment is done
        if(prescription==null|| prescription.getIsDone()||prescription.getAppointment().getIsDone()){
            return "gagal-save-prescription";
        }

        // check if stock enough
        for (DrugPrescriptionModel drugPrescription : prescription.getListPrescribe()){
            Long stock = drugService.getDrug(drugPrescription.getDrug().getId()).getStock();
            if(stock< drugPrescription.getQuantity()){
                return "gagal-save-prescription";
            }
        }

        prescription.setIsDone(true);
        prescription.setConfirmer(apoteker);
        prescriptionService.update(prescription);

        // check amount
        for (DrugPrescriptionModel drugPrescription : prescription.getListPrescribe()){
            Long stock = drugService.getDrug(drugPrescription.getDrug().getId()).getStock();
            if(stock< drugPrescription.getQuantity()){
                return "gagal-save-prescription";
            }
        }
    
    
        // Invoice
        InvoiceModel invoice = new InvoiceModel();
        invoice.setDateIssued(LocalDateTime.now());
        invoice.setAppointment(prescription.getAppointment());
        invoice.setIsPaid(false);
        invoice.setAmount(prescription.getAppointment().getDoctor().getFee()+ totalBill(prescription));
        invoiceService.add(invoice);

        return "save-prescription";
    }

    private Long totalBill(PrescriptionModel prescription){
        if(prescription != null ){
            if (prescription.getListPrescribe().size() == 0){
                return Long.valueOf(0);
            }else{
                Long total = Long.valueOf(0);
                for(DrugPrescriptionModel drugPrecription : prescription.getListPrescribe()){
                    total += drugPrecription.getDrug().getPrice() * drugPrecription.getQuantity();
                }

                return total;
            }
        } return Long.valueOf(0);
    }



}
