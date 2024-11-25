package com.udev.hotel.domain.entity;

import com.udev.hotel.config.constants.EtatChambre;
import com.udev.hotel.config.constants.TypeChambre;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "chambre")
public class Chambre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "numeroChambre", nullable = false)
	private int numeroChambre;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(name = "etat", nullable = false)
	private EtatChambre etat;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(name = "type", nullable = false)
	private TypeChambre type;

	@NotNull
	@Column(name = "prix", nullable = false)
	private double prix;

	@Column(name = "description", nullable = false)
	private String description;

	@Lob
	@Column(name = "photos")
	@NotNull
	private byte[] photos;

	public Chambre() {
	}

	public Chambre(@NotNull int numeroChambre, @NotNull EtatChambre etat, @NotNull TypeChambre type,
			@NotNull double prix, String description, @NotNull byte[] photos) {
		super();
		this.numeroChambre = numeroChambre;
		this.etat = etat;
		this.type = type;
		this.prix = prix;
		this.description = description;
		this.photos = photos;
	}

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

	public TypeChambre getType() {
		return type;
	}

	public void setType(TypeChambre type) {
		this.type = type;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public EtatChambre getEtat() {
		return etat;
	}

	public void setEtat(EtatChambre etat) {
		this.etat = etat;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getPhotos() {
		return photos;
	}

	public void setPhotos(byte[] photos) {
		this.photos = photos;
	}

}
