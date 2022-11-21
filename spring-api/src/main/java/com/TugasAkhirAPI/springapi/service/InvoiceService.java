package com.TugasAkhirAPI.springapi.service;

import com.TugasAkhirAPI.springapi.repository.InvoiceDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {
    @Autowired
    InvoiceDB invoiceDB;
}
