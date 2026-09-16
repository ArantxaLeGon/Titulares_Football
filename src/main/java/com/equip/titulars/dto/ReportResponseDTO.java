package com.equip.titulars.dto;

import lombok.Data;

@Data
public class ReportResponseDTO {
    private Long idPlayer;
    private Long numTraining;
    private Double power;
    private Double speed;
    private Double passing;
    private Double pointsTraining;
    private Double finalPoints;
}
