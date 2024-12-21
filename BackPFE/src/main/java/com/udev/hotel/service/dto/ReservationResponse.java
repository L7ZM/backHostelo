package com.udev.hotel.service.dto;

import java.time.LocalDate;
import java.util.List;

import com.udev.hotel.config.constants.ReservationStatus;

import lombok.Data;
@Data
public class ReservationResponse {
    private Long id;
    private Long idUser;
    private int numeroChambre;
    private String nom;
    private String prenom;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private ReservationStatus status;
    public ReservationResponse(Long id, Long idUser, int numeroChambre, String nom, String prenom, 
            LocalDate dateDebut, LocalDate dateFin, ReservationStatus status) {
		super();
		this.id = id;
		this.idUser = idUser;
		this.numeroChambre = numeroChambre;
		this.nom = nom;
		this.prenom = prenom;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.status = status;
	}

 
}
