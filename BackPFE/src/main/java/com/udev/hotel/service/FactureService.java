package com.udev.hotel.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udev.hotel.config.constants.PaimentStatus;
import com.udev.hotel.config.constants.ReservationStatus;
import com.udev.hotel.domain.entity.Facture;
import com.udev.hotel.domain.entity.Reservation;
import com.udev.hotel.domain.entity.ReservationServiceAdd;
import com.udev.hotel.domain.repository.FactureRepository;
import com.udev.hotel.domain.repository.ReservationRepository;
import com.udev.hotel.domain.repository.ReservationServiceAddRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FactureService {
	private final FactureRepository factureRepository;
	private final ReservationRepository reservationRepository;
	private final ReservationServiceAddRepository reservationServiceAddRepository;

	public Facture genererFacture(Long reservationId) {
		Reservation reservation = reservationRepository.findById(reservationId)
				.orElseThrow(() -> new IllegalArgumentException("Réservation introuvable."));
		
		double montantTotal = calculerMontantTotal(reservation);

		Facture facture = new Facture();
		facture.setDateEmission(LocalDate.now());
		facture.setMontantTotal(montantTotal);
		facture.setEtatPaiement(PaimentStatus.NOT_PAYED);
		facture.setReservation(reservation);

		return factureRepository.save(facture);
	}

	public Facture payerFacture(Long factureId) {
		Facture facture = factureRepository.findById(factureId)
				.orElseThrow(() -> new IllegalArgumentException("Facture introuvable."));

		facture.setEtatPaiement(PaimentStatus.PAID);
		return factureRepository.save(facture);
	}

	private double calculerMontantTotal(Reservation reservation) {
		double montantChambre = reservation.getChambre().getPrix();

		List<ReservationServiceAdd> services = reservationServiceAddRepository.findByReservation(reservation);
		double montantServices = services.stream().mapToDouble(service -> service.getServiceAdditionnel().getPrix())
				.sum();

		return montantChambre + montantServices;
	}
}
