package com.udev.hotel.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ReservationServiceAdd")
public class ReservationServiceAdd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Reservation reservation;

    @ManyToOne
    private ServiceAdditionnel serviceAdditionnel;

    public ReservationServiceAdd() {
    	
    }

	public ReservationServiceAdd(Long id, Reservation reservation, ServiceAdditionnel serviceAdditionnel) {
		super();
		this.id = id;
		this.reservation = reservation;
		this.serviceAdditionnel = serviceAdditionnel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public ServiceAdditionnel getServiceAdditionnel() {
		return serviceAdditionnel;
	}

	public void setServiceAdditionnel(ServiceAdditionnel serviceAdditionnel) {
		this.serviceAdditionnel = serviceAdditionnel;
	}
    
    
}
