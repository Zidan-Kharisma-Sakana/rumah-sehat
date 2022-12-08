package com.TugasAkhirAPI.springapi.rest_controller;

import com.TugasAkhirAPI.springapi.model.AppointmentModel;
import com.TugasAkhirAPI.springapi.model.InvoiceModel;
import com.TugasAkhirAPI.springapi.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

// Notes: Please use english verb/adjective to describe your path
@RestController
@RequestMapping("/api/invoice")
public class InvoiceRESTController {
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping(value = "/get-all/{username}")
    private List<InvoiceModel> retrieveListInvoice(@PathVariable("username") String username) {
        List<InvoiceModel> listInvoice = invoiceService.retrieveListInvoiceByPatientUsername(username);
        return listInvoice;
    }
}
