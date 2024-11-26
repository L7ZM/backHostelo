package com.udev.hotel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udev.hotel.config.constants.EtatChambre;
import com.udev.hotel.domain.entity.Chambre;
import com.udev.hotel.domain.repository.ChambreRepository;

@Service
public class ChambreService {

    @Autowired
    private ChambreRepository chambreRepository;

    public List<Chambre> getAllChambres() {
        return chambreRepository.findAll();
    }

    public Optional<Chambre> getChambreById(Long id) {
        return chambreRepository.findById(id);
    }

    public Chambre createChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    public Chambre updateChambre(Long id, Chambre updatedChambre) {
        return chambreRepository.findById(id).map(existingChambre -> {
            existingChambre.setNumeroChambre(updatedChambre.getNumeroChambre());
            existingChambre.setType(updatedChambre.getType());
            existingChambre.setPrix(updatedChambre.getPrix());
            existingChambre.setEtat(updatedChambre.getEtat());
            existingChambre.setDescription(updatedChambre.getDescription());
            existingChambre.setPhotos(updatedChambre.getPhotos());
            return chambreRepository.save(existingChambre);
        }).orElseThrow(() -> new RuntimeException("Chambre not found with id " + id));
    }

    public void deleteChambre(Long id) {
        chambreRepository.deleteById(id);
    }

    public List<Chambre> getAvailableChambres() {
        return chambreRepository.findByEtat(EtatChambre.DISPONIBLE);
    }
}