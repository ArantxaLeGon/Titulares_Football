package com.equip.titulars.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.equip.titulars.dto.ReportRequestDTO;
import com.equip.titulars.dto.ReportResponseDTO;
import com.equip.titulars.entity.ReportId;
import com.equip.titulars.entity.Reports;
import com.equip.titulars.repository.ReportRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;

// Crear los reportes
    public ReportResponseDTO createReport (ReportRequestDTO request) throws Exception{
        // Crea el un objeto del id
        ReportId id = new ReportId();
        id.setIdPlayer(request.getIdPlayer());
        id.setNumTraining(request.getNumTraining());

        // Revision de que no se repita el id
        Optional<Reports> foundReport = reportRepository.findById(id);

        //Verificacion si exiat en la base de datos o no
        if (foundReport.isPresent()) {
            throw new Exception("Este jugador ya tiene el reporte del Entreno");
        }

        // Guarda datos del reporte
        Reports report = new Reports();
        report.setId(id);
        report.setPower(request.getPower());//20
        report.setSpeed(request.getSpeed());//30
        report.setPassing(request.getPassing());//50
        report.setPointsTraining((request.getPower()*0.2)+(request.getSpeed()*0.3)+(request.getPassing()*0.5));
        report.setFinalPoints(0.0);
        reportRepository.save(report);

        // Muestra datos guardados
        ReportResponseDTO response= new ReportResponseDTO();
        response.setIdPlayer(request.getIdPlayer());
        response.setNumTraining(request.getNumTraining());
        response.setPower(report.getPower());
        response.setSpeed(report.getSpeed());
        response.setPassing(report.getPassing());
        response.setPointsTraining(report.getPointsTraining());
        response.setFinalPoints(report.getFinalPoints());

        return  response;
    }
    
    //Metodos para el get
    
    public List<ReportResponseDTO> createArrayPlayers(ReportResponseDTO report) throws Exception{
        
        List<ReportResponseDTO> finalPointsList = new ArrayList<>();

        if(reportRepository.countByIdIdPlayer(report.getIdPlayer()) == 3 ){

                // Hace uan liata que pasa por los ids de los jugadores
                List<Reports> reportOnePlayer = reportRepository.findByIdIdPlayer(report.getIdPlayer());

                //Indica que inicia en 0.0
                report.setPower(0.0);
                report.setSpeed(0.0);
                report.setPassing(0.0);
                report.setPointsTraining(0.0);
                report.setFinalPoints(0.0);

                //Ciclo que pase jugador por jugador y lo guarda en el array
                for (Reports reportPlayer : reportOnePlayer) {
                    report.setNumTraining(reportPlayer.getId().getNumTraining());
                    report.setFinalPoints(report.getFinalPoints() + reportPlayer.getPointsTraining());
                    report.setPower(report.getPower() + reportPlayer.getPower());
                    report.setSpeed(report.getSpeed() + reportPlayer.getSpeed());
                    report.setPassing(report.getPassing() + reportPlayer.getPassing());
                    report.setPointsTraining(report.getPointsTraining() + reportPlayer.getPointsTraining());
                }
                // Crear promedio de cada valor
                report.setPower(report.getPower()/3);
                report.setSpeed(report.getSpeed()/3);
                report.setPassing(report.getPassing()/3);
                report.setPointsTraining(report.getPointsTraining()/3);

                finalPointsList.add(report);
            }else{
                throw new Exception("Datos insuficientes");
            }
            return finalPointsList;
    }


    public List<ReportResponseDTO> startingPlayersSelector(List<ReportResponseDTO> finalPointsList){
        // Ordenado del array de mayor a menor por los untos totales  y mostrasndo los necesarios
            finalPointsList.sort((a, b) -> b.getFinalPoints().compareTo(a.getFinalPoints()));

            while (finalPointsList.size() > 5) {
                finalPointsList.remove(finalPointsList.size() - 1);
            }
            return  finalPointsList;     
    } // 7 total, 5 juegan


// Extraer registros para mostrar titulares
    public List<ReportResponseDTO> getAllReports() throws Exception{ // 7 total, 5 juegan

        // Crea Array para lista de reportes
        List<Reports> foundTrainings = reportRepository.findAll();

        // Lista en la que se almacenara todos los jugadores
        List<ReportResponseDTO> finalPointsList = new ArrayList<>();

        // Ciclo para pasar registro por registro
        for(Reports foundTraining : foundTrainings){
            ReportResponseDTO report = new ReportResponseDTO();
            report.setIdPlayer(foundTraining.getId().getIdPlayer());

            // Revision si existe el jugador o no
            Boolean alreadyExist= false;
            for(ReportResponseDTO finalPoints : finalPointsList){
                if(finalPoints.getIdPlayer().equals(foundTraining.getId().getIdPlayer())){
                    alreadyExist= true;
                    break;
                }
            }

            //Si no existe se añade al array
            if(alreadyExist){
                continue;
            }
                
            // Metodo que añade los datos al array
            createArrayPlayers(report);
            finalPointsList.add(report);

            // Metodo que selecciona los titulares
            startingPlayersSelector(finalPointsList);
            
        }
        
        return finalPointsList;
        
    }
}