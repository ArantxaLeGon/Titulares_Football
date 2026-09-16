package com.equip.titulars.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.equip.titulars.dto.ReportRequestDTO;
import com.equip.titulars.dto.ReportResponseDTO;
import com.equip.titulars.service.ReportService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;

    @PostMapping("/create")
    public ResponseEntity<ReportResponseDTO> createReport(@RequestBody ReportRequestDTO request)throws Exception{
        ReportResponseDTO response = reportService.createReport(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/starting_players")
    public ResponseEntity<List<ReportResponseDTO>> getAllReports() throws Exception{
        List<ReportResponseDTO> response = reportService.getAllReports();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
    
