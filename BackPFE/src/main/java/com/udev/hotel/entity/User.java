package com.udev.hotel.entity;

import java.time.LocalDate;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.udev.hotel.config.Constants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Size(min = 1, max = 50)
    @Column(name = "last_name", length = 50)
    @NotNull
    private String nom;

    @Size(min = 1, max = 50)
    @Column(name = "first_name", length = 50)
    @NotNull
    private String prenom;
    

    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    @Column(name = "email" , unique = true)  
    @NotNull
    private String email;
    
    @JsonIgnore
    @NotNull
    @Column(name = "password", length = 60)
    private String password;
    
    @Size(min = 1, max = 50)
    @Column(name = "adresse")
    private String adresse;
    
    @Column(name = "num_tel")
    private String telephone;
    
    @Column(name = "pointFidelite")
    private int pointsFidelite;
    
    
    @Column(name = "dateNaissance")
    private LocalDate dateNaissance;

    @ManyToOne
    private Role role;

    
	public User() {
		super();
	}
	

	public User(Long id, String nom, String prenom, String email, String password, String adresse, String telephone,
			int pointsFidelite, LocalDate dateNaissance, Role role) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.adresse = adresse;
		this.telephone = telephone;
		this.pointsFidelite = pointsFidelite;
		this.dateNaissance = dateNaissance;
		this.role = role;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getPointsFidelite() {
		return pointsFidelite;
	}

	public void setPointsFidelite(int pointsFidelite) {
		this.pointsFidelite = pointsFidelite;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
