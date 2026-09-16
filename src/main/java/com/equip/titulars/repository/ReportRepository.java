package com.equip.titulars.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.equip.titulars.entity.Reports;
import com.equip.titulars.entity.ReportId;

public interface ReportRepository extends JpaRepository<Reports,ReportId>{
    Long countByIdIdPlayer(Long idPlayer);

    List<Reports> findByIdIdPlayer(Long idPlayer);
}
