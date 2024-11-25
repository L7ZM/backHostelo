package com.udev.hotel.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udev.hotel.config.constants.EtatChambre;
import com.udev.hotel.config.constants.TypeChambre;
import com.udev.hotel.domain.entity.Chambre;

public interface ChambreRepository extends JpaRepository<Chambre, Long> {
    List<Chambre> findByType(TypeChambre type);
    List<Chambre> findByEtat(EtatChambre etat);
}