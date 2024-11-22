package com.udev.hotel.domain.entity;

import com.udev.hotel.config.TypeChambre;

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
	private TypeChambre type;

	@NotNull
	@Column(name = "prix", nullable = false)
	private double prix;

	@NotNull
	@Column(name = "etat", nullable = false)
	private String etat;

	@Column(name = "description", nullable = false)
	private String description;

	@Lob
	@Column(name = "photos")
	@NotNull
	private byte[] photos;

	public Chambre() {
	}

	public Chambre(Long id, @NotNull int numeroChambre, @NotNull TypeChambre type, @NotNull double prix,
			@NotNull String etat, String description, byte[] photos) {
		super();
		this.id = id;
		this.numeroChambre = numeroChambre;
		this.type = type;
		this.prix = prix;
		this.etat = etat;
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

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
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
