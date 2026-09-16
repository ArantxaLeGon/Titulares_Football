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
    public ResponseEntity<ReportResponseDTO> createreport(@RequestBody ReportRequestDTO request)throws Exception{
        ReportResponseDTO response = reportService.createreport(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/starting_players")
    public ResponseEntity<List<ReportResponseDTO>> getAllreports() throws Exception{
        List<ReportResponseDTO> response = reportService.getAllreports();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
    
