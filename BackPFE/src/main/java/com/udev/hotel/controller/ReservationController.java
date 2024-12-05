package com.udev.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udev.hotel.config.security.AuthoritiesConstants;
import com.udev.hotel.domain.entity.Reservation;
import com.udev.hotel.service.ReservationService;
import com.udev.hotel.service.dto.ReservationRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

	@Autowired
    private ReservationService reservationService;

    @PostMapping
    @Secured({AuthoritiesConstants.USER , AuthoritiesConstants.ADMIN})
    public ResponseEntity<Reservation> reserver(@RequestBody ReservationRequest request) {
        Reservation reservation = reservationService.createReservation(
                request.getUserId(),
                request.getChambreId(),
                request.getServiceIds(),
                request.getDateDebut(),
                request.getDateFin()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(reservation);
    }
}