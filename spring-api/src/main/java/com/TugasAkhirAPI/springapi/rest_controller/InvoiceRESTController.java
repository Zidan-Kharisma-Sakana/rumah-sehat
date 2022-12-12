package com.TugasAkhirAPI.springapi.rest_controller;

import com.TugasAkhirAPI.springapi.model.AppointmentModel;
import com.TugasAkhirAPI.springapi.model.InvoiceModel;
<<<<<<< HEAD
import com.TugasAkhirAPI.springapi.model.User.PatientModel;
import com.TugasAkhirAPI.springapi.service.InvoiceService;
import com.TugasAkhirAPI.springapi.service.User.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
=======
import com.TugasAkhirAPI.springapi.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
>>>>>>> fa21fdf82238a20791a878f804b831c5fdc8974c
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
=======
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
>>>>>>> fa21fdf82238a20791a878f804b831c5fdc8974c

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceRESTController {
    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    PatientService patientService;

    @GetMapping(value = "/get-all/{username}")
    private List<InvoiceModel> retrieveListInvoice(@PathVariable("username") String username) {
        List<InvoiceModel> listInvoice = invoiceService.retrieveListInvoiceByPatientUsername(username);
        return listInvoice;
    }
<<<<<<< HEAD

    @GetMapping(value = "/detail/{code}")
    private InvoiceModel getInvoice(@PathVariable("code") String code) {
        InvoiceModel invoice = invoiceService.getInvoiceByCode(code);
        return invoice;
    }

    @PutMapping(value = "/settle/{code}")
    private ResponseEntity<?> settleInvoice(@PathVariable("code") String code,Principal principal) {
        InvoiceModel invoice = invoiceService.getInvoiceByCode(code);
        PatientModel patient = patientService.getPatientByUsername(principal.getName());

        if(patient.getBalance()>=invoice.getAmount()){
            patient.setBalance(patient.getBalance()-invoice.getAmount());
            invoice.setIsPaid(true);
            invoiceService.update(invoice);
            patientService.update(patient);

            // return success
            return ResponseEntity.ok(invoice);
        }
        // return failed
        throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST, "Saldo tidak cukup"
    );
        
        
    }

=======
>>>>>>> fa21fdf82238a20791a878f804b831c5fdc8974c
}
