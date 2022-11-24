package com.TugasAkhir.spring.service;

import com.TugasAkhir.spring.model.InvoiceModel;
import com.TugasAkhir.spring.repository.InvoiceDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {
    @Autowired
    InvoiceDB invoiceDB;

    public InvoiceModel add(InvoiceModel invoice){
        return invoiceDB.save(invoice);
    }
}
