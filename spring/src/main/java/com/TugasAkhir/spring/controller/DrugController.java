package com.TugasAkhir.spring.controller;

import com.TugasAkhir.spring.model.DrugModel;
import com.TugasAkhir.spring.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

// Notes: Please use english verb/adjective to describe your path
@Controller
@RequestMapping("/drug")
public class DrugController {

    @Autowired
    private DrugService drugService;

    @GetMapping({"/all"})
    public String listDrug(Model model) {
        List<DrugModel> listDrug = this.drugService.getListDrug();
        model.addAttribute("listDrug", listDrug);
        return "viewall-drugs";
    }

    @GetMapping({"/edit/{id}"})
    public String updateDrugFormPage(@PathVariable String id, Model model) {
        DrugModel drug = this.drugService.getDrug(id);
        model.addAttribute("drug", drug);
        return "form-update-drug";
    }

    @PostMapping({"/edit"})
    public String updateDrugSubmitPage(@ModelAttribute DrugModel drug, Model model) {
        DrugModel updatedDrug = this.drugService.updateDrug(drug);
        List<DrugModel> listDrug = this.drugService.getListDrug();
        model.addAttribute("drug", updatedDrug);
        return "update-drug";
    }

    @GetMapping({"/detail/{id}"})
    public String viewDetailDrug(@PathVariable("id") String id, Model model) {
        DrugModel drug = this.drugService.getDrug(id);
        model.addAttribute("drug", drug);
        return "view-drug";
    }



}
