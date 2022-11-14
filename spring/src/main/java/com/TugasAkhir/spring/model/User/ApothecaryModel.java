package com.TugasAkhir.spring.model.User;

import com.TugasAkhir.spring.model.PrescriptionModel;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "apothecary")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApothecaryModel extends BaseUser {
    @Id
    private String uuid;

    @OneToMany(mappedBy = "confirmer")
    private List<PrescriptionModel> listConfirmed;
}
