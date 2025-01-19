package com.udev.hotel.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udev.hotel.domain.entity.Facture;
import com.udev.hotel.domain.repository.FactureRepository;
import com.udev.hotel.service.FactureService;
import com.udev.hotel.service.dto.FactureDTO;
import com.udev.hotel.service.mapper.FactureMapper;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/factures")
@RequiredArgsConstructor
public class FactureController {
	   private final FactureService factureService;
		private final FactureRepository factureRepository;
		@Autowired
		private FactureMapper factureMapper;


	    @PostMapping("/generer/{reservationId}")
	    public ResponseEntity<Facture> genererFacture(@PathVariable Long reservationId) {
	        Facture facture = factureService.genererFacture(reservationId);
	        return ResponseEntity.status(HttpStatus.CREATED).body(facture);
	    }
	    
	    @GetMapping("/reservation/{reservationId}")
	    public ResponseEntity<FactureDTO> getFactureByReservation(@PathVariable Long reservationId) {
	        FactureDTO factureDTO = factureService.getFactureByReservation(reservationId);
	        return ResponseEntity.ok(factureDTO);
	    }
	    
	    @GetMapping("/{reservationId}")
	    public ResponseEntity<FactureDTO> getOrGenerateFacture(@PathVariable Long reservationId) {
	        Optional<FactureDTO> existingFacture = factureRepository.getFactureByReservationId(reservationId) ;
	        if (existingFacture.isPresent()) {
	            return ResponseEntity.ok(existingFacture.get());
	        }else {
	        	Facture generatedFacture = factureService.genererFacture(reservationId);
	        	FactureDTO generatedFactureDTO = factureMapper.toFactureDTO(generatedFacture);
	        	
	        	return ResponseEntity.status(HttpStatus.CREATED).body(generatedFactureDTO);
	        }

	       
	    }

	    @PostMapping("/payer/{factureId}")
	    public ResponseEntity<Facture> payerFacture(@PathVariable Long factureId) {
	        Facture facture = factureService.payerFacture(factureId);
	        return ResponseEntity.ok(facture);
	    }
	    
	    
	    @GetMapping("/{factureId}/report")
		public void generateFactureReport(@PathVariable Long factureId, HttpServletResponse response) throws Exception {
			factureService.generateFactureReport(factureId, response);
		}
}
