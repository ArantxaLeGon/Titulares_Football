package com.equip.titulars.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class ReportId {
    @Column(name="id_player")
    private Long idPlayer;
    @Column(name="num_training")
    private Long numTraining;
}
