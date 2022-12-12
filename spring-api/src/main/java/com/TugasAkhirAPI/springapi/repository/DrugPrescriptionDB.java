package com.TugasAkhirAPI.springapi.repository;





import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.TugasAkhirAPI.springapi.model.DrugPrescriptionModel;

@Repository
public interface  DrugPrescriptionDB extends JpaRepository<DrugPrescriptionModel, String> {
    @Query(value="SELECT * FROM drug_prescription WHERE prescription_id=:id",nativeQuery = true)
    List<DrugPrescriptionModel> findListDrugPrescription(@Param("id") Long id);
}