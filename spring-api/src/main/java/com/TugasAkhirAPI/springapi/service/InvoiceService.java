package com.TugasAkhirAPI.springapi.service;

import com.TugasAkhirAPI.springapi.model.AppointmentModel;
import com.TugasAkhirAPI.springapi.model.InvoiceModel;
import com.TugasAkhirAPI.springapi.repository.AppointmentDB;
import com.TugasAkhirAPI.springapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    @Autowired
    InvoiceDB invoiceDB;

    @Autowired
    AppointmentDB appointmentDB;

    public List<InvoiceModel> retrieveListInvoiceByPatientUsername(String username) {
        List<Optional<InvoiceModel>> listOptInvoice = invoiceDB.findAllByAppointment_Patient_Username(username);
        List<InvoiceModel> listInvoice = new ArrayList<>();
        Iterator<Optional<InvoiceModel>> it = listOptInvoice.iterator();
        while (it.hasNext()) {
            Optional<InvoiceModel> optInvoice = it.next();
            if (optInvoice.isPresent()) {
                InvoiceModel invoice = optInvoice.get();
                listInvoice.add(invoice);
            }
        }
        return listInvoice;
    }

    public InvoiceModel getInvoiceByCode(String code) {
        return invoiceDB.findByCode(code);
    }

    public InvoiceModel payInvoice (InvoiceModel invoice){
        invoice.setIsPaid(Boolean.TRUE);
        return invoice;
    }


}
