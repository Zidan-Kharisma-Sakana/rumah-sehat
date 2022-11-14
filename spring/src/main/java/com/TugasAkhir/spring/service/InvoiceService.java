package com.TugasAkhir.spring.service;

import com.TugasAkhir.spring.repository.InvoiceDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {
    @Autowired
    InvoiceDB invoiceDB;
}
