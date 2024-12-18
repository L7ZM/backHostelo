package com.udev.hotel.domain.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.udev.hotel.config.constants.ReservationStatus;
import com.udev.hotel.domain.entity.Reservation;
import com.udev.hotel.service.dto.ReservationRequest;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	List<Reservation> findByStatus(@Param("status") ReservationStatus status);

	@Query("SELECT r FROM Reservation r WHERE r.chambre.id = :chambreId AND "
			+ "(r.dateDebut < :dateFin AND r.dateFin > :dateDebut)")
	List<Reservation> findByChambreAndDateOverlap(@Param("chambreId") Long chambreId,
			@Param("dateDebut") LocalDate dateDebut, @Param("dateFin") LocalDate dateFin);

	@Query("SELECT r FROM Reservation r WHERE r.user.email = :username")
	List<Reservation> getReservationsByUsername(@Param("username") String username);

	@Query("SELECT new com.udev.hotel.service.dto.ReservationRequest(r.id ,r.chambre.numeroChambre, r.dateDebut, r.dateFin, r.status) " +
		       "FROM Reservation r " +
		       "WHERE r.user.email = :username")
	List<ReservationRequest> ReservationsByUsername(@Param("username") String username);

}
