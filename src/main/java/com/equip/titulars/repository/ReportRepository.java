package com.equip.titulars.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

import com.equip.titulars.entity.Reports;
import com.equip.titulars.entity.ReportId;



// @Repository
public interface ReportRepository extends JpaRepository<Reports,ReportId>{
    Optional<Reports> findById(ReportId id);

    List<Reports> findAll();

    Long countByIdIdPlayer(Long idPlayer);

    List<Reports> findByIdIdPlayer(Long idPlayer);
}
