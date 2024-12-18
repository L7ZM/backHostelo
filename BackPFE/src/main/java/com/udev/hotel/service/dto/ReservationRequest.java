package com.udev.hotel.service.dto;

import java.time.LocalDate;

import com.udev.hotel.config.constants.ReservationStatus;

public class ReservationRequest {
	private Long id;
	private int numeroChambre;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private ReservationStatus status;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getNumeroChambre() {
		return numeroChambre;
	}
	public void setNumeroChambre(int numeroChambre) {
		this.numeroChambre = numeroChambre;
	}
	public LocalDate getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}
	public LocalDate getDateFin() {
		return dateFin;
	}
	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}
	public ReservationStatus getStatus() {
		return status;
	}
	public void setStatus(ReservationStatus status) {
		this.status = status;
	}
	public ReservationRequest(Long id,int numeroChambre, LocalDate dateDebut, LocalDate dateFin, ReservationStatus status) {
		super();
		this.id = id;
		this.numeroChambre = numeroChambre;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.status = status;
	}
	
	
}
