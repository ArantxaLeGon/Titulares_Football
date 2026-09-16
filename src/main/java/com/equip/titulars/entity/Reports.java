package com.equip.titulars.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name="Equip")
public class Reports {
    @EmbeddedId 
    private ReportId id;
    @Column(name="power")
    private Double power;
    @Column(name="speed")
    private Double speed;
    @Column(name="passing")
    private Double passing;
    @Column(name="pointsTraining")
    private Double pointsTraining;
    @Transient 
    private Double finalPoints;
}
