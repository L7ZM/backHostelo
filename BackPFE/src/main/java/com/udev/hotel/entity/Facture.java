package com.udev.hotel.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "facture")
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(name = "dateEmission")
    private LocalDate dateEmission;
    
    @NotNull
    @Column(name = "montantTotal")
    private double montantTotal;
    
    @Column(name = "etatPaiment")
    private String etatPaiement;

    @OneToOne
    private Reservation reservation;

    public Facture() {
    	
    }
    
	public Facture(Long id, LocalDate dateEmission, double montantTotal, String etatPaiement, Reservation reservation) {
		super();
		this.id = id;
		this.dateEmission = dateEmission;
		this.montantTotal = montantTotal;
		this.etatPaiement = etatPaiement;
		this.reservation = reservation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDateEmission() {
		return dateEmission;
	}

	public void setDateEmission(LocalDate dateEmission) {
		this.dateEmission = dateEmission;
	}

	public double getMontantTotal() {
		return montantTotal;
	}

	public void setMontantTotal(double montantTotal) {
		this.montantTotal = montantTotal;
	}

	public String getEtatPaiement() {
		return etatPaiement;
	}

	public void setEtatPaiement(String etatPaiement) {
		this.etatPaiement = etatPaiement;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	
    
}

