package com.equip.titulars.dto;

import lombok.Data;

@Data
public class ReportRequestDTO {
    private Long idPlayer;
    private Long numTraining;
    private Double power;
    private Double speed;
    private Double passing;
}
