package com.udev.hotel.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.udev.hotel.config.constants.EtatChambre;
import com.udev.hotel.config.constants.TypeChambre;
import com.udev.hotel.domain.entity.Chambre;

public interface ChambreRepository extends JpaRepository<Chambre, Long> {
	List<Chambre> findByType(TypeChambre type);

	List<Chambre> findByEtat(EtatChambre etat);

	@Query(value = "SELECT cp.photo_data " + "FROM chambre_photos cp " + "JOIN chambre c ON c.id = cp.chambre_id "
			+ "WHERE c.numero_chambre = :numC", nativeQuery = true)
	List<byte[]> findPhotosByChambreId(@Param("numC") Long numC);

	@Query("SELECT c FROM Chambre c LEFT JOIN FETCH c.photos WHERE c.id = :id")
	Optional<Chambre> findByIdWithPhotos(@Param("id") Long id);
}