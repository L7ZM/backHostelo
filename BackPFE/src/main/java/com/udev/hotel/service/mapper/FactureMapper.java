package com.udev.hotel.service.mapper;

import org.springframework.stereotype.Service;

import com.udev.hotel.domain.entity.Facture;
import com.udev.hotel.service.dto.FactureDTO;

@Service
public class FactureMapper {
	public FactureDTO toFactureDTO(Facture facture) {
	    FactureDTO dto = new FactureDTO();
	    dto.setId(facture.getId());
	    dto.setMontantTotal(facture.getMontantTotal());
	    dto.setDateEmission(facture.getDateEmission());
	    dto.setEtatPaiement(facture.getEtatPaiement());
	    return dto;
	}
}
