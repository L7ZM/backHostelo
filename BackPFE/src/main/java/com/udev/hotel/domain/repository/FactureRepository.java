package com.udev.hotel.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udev.hotel.domain.entity.Facture;

public interface FactureRepository extends JpaRepository<Facture, Long>{

}
