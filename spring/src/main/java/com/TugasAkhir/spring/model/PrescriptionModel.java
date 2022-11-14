package com.TugasAkhir.spring.model;

import com.TugasAkhir.spring.model.User.ApothecaryModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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
}
