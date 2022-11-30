package com.TugasAkhirAPI.springapi.model;

import com.TugasAkhirAPI.springapi.model.User.ApothecaryModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="prescription")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionModel {
    @Id
    private Long id;

    @NotNull
    private Boolean isDone;

    @NotNull
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "confirmer_uuid")
    private ApothecaryModel confirmer;

    @OneToMany(mappedBy = "prescription",fetch = FetchType.EAGER)
    private List<DrugPrescriptionModel> listPrescribe;

    @OneToOne
    @JoinColumn(name = "appointment_code")
    private AppointmentModel appointment;
}
