package com.udev.hotel.domain.entity;

import java.time.ZonedDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(name = "date_debut", nullable = false)
    private ZonedDateTime dateDebut;
    
    @NotNull
    @Column(name = "date_fin", nullable = false)
    private ZonedDateTime dateFin;
    
    @Column(name = "etat", nullable = false)
    private String etat;

    @ManyToOne
    private User client;

    @ManyToOne
    private Chambre chambre;

    public Reservation(){
    	
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ZonedDateTime getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(ZonedDateTime dateDebut) {
		this.dateDebut = dateDebut;
	}

	public ZonedDateTime getDateFin() {
		return dateFin;
	}

	public void setDateFin(ZonedDateTime dateFin) {
		this.dateFin = dateFin;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public Chambre getChambre() {
		return chambre;
	}

	public void setChambre(Chambre chambre) {
		this.chambre = chambre;
	}
    
}
