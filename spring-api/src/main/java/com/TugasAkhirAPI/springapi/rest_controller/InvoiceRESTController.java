package com.TugasAkhirAPI.springapi.rest_controller;

import com.TugasAkhirAPI.springapi.model.InvoiceModel;
import com.TugasAkhirAPI.springapi.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

// Notes: Please use english verb/adjective to describe your path
@Slf4j
@RestController
@RequestMapping("/api/invoice")
public class InvoiceRESTController {
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping(value = "/get-all/{username}")
    private List<InvoiceModel> retrieveListInvoice(@PathVariable("username") String username) {
        List<InvoiceModel> listInvoice = invoiceService.retrieveListInvoiceByPatientUsername(username);
        log.info("Try to get all invoice");
        return listInvoice;
    }

    @GetMapping(value = "/detail/{id}")
    private InvoiceModel detailInvoice(@PathVariable("id") String id) {
        try{
            log.info("Try to get detail invoice");
            InvoiceModel invoice = invoiceService.getInvoiceByCode(id);
            if (invoice != null){
                log.info("Invoice founded");
            }
            return invoice;
        }
        catch (NoSuchElementException e){
            log.info("Invoice not found");
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Invoice with id" + id + " not found"
            );
        }
    }

    @PutMapping(value = "/settle/{id}")
    private  InvoiceModel payInvoice(@PathVariable("id")String id, @RequestBody InvoiceModel invoice){
        try{
            InvoiceModel invoicePaid = invoiceService.payInvoice(invoice);
            log.info("Try to pay invoice");
            return  invoicePaid;
        } catch (NoSuchElementException e){
            throw  new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Invoice with id" + id + " not found"
            );
        }
    }


}
