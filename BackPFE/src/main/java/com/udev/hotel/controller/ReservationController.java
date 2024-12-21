package com.udev.hotel.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udev.hotel.config.security.AuthoritiesConstants;
import com.udev.hotel.domain.entity.Facture;
import com.udev.hotel.domain.entity.Reservation;
import com.udev.hotel.domain.repository.FactureRepository;
import com.udev.hotel.domain.repository.ReservationRepository;
import com.udev.hotel.service.ReservationService;
import com.udev.hotel.service.dto.ReservationDTO;
import com.udev.hotel.service.dto.ReservationRequest;
import com.udev.hotel.service.dto.ReservationResponse;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

	private final Logger log = LoggerFactory.getLogger(ReservationController.class);

	@Autowired
    private ReservationService reservationService;
	@Autowired
	private ReservationRepository reservationRepository;
	
    @PostMapping
    @Secured({AuthoritiesConstants.USER , AuthoritiesConstants.ADMIN})
    public ResponseEntity<Reservation> reserver(@RequestBody ReservationDTO request) {
    	String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info(currentUsername);
        Reservation reservation = reservationService.createReservation(
                currentUsername,
                request.getChambreId(),
                request.getServiceIds(),
                request.getDateDebut(),
                request.getDateFin()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(reservation);
    }
    
    @GetMapping
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<List<ReservationResponse>> getAllReservation(){
    	List<ReservationResponse> reservation = reservationRepository.getAllreservation(); 
		return new ResponseEntity<>(reservation, HttpStatus.OK);
    }
      
    @GetMapping("/myBooking")
    @Timed
    public ResponseEntity<List<ReservationRequest>> getReservationsByUserId() {
    	String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        List<ReservationRequest> reservations = reservationService.getReservationsByUsername(currentUsername);
        return ResponseEntity.ok(reservations);
    }
    
    @DeleteMapping("/{idReservation}")
    @Timed
    public ResponseEntity<Void> cancelReservation(@PathVariable Long idReservation) {
    	log.debug("REST request to delete Reservation: {}", idReservation);
    	reservationService.cancelReservation(idReservation);
		return ResponseEntity.ok().build();
    }
    
    @PutMapping("/{id}/validate")
	@Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<String> validateReservation(@PathVariable Long id) {
        reservationService.validateReservation(id);
        return ResponseEntity.ok("Reservation has been confirmed successfully.");
    }
    
    
 

}