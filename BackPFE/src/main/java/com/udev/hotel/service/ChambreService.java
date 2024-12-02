package com.udev.hotel.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.udev.hotel.config.constants.EtatChambre;
import com.udev.hotel.domain.entity.Chambre;
import com.udev.hotel.domain.repository.ChambreRepository;
import com.udev.hotel.service.dto.ChambreDTO;

@Service
public class ChambreService {

    @Autowired
    private ChambreRepository chambreRepository;

    private final String uploadDir = System.getProperty("user.home") + "/Documents/hotel-photos/";     
      
    public List<Chambre> getAllChambres() {
        return chambreRepository.findAll();
    }

    public Optional<Chambre> getChambreById(Long id) {
        return chambreRepository.findById(id);
    }
    
    public List<byte[]> getPhotosByChambreId(Long numC) {
        return chambreRepository.findPhotosByChambreId(numC);
    }
    
    public Optional<ChambreDTO> getChambreWithPhotos(Long id) {
        return chambreRepository.findById(id).map(this::mapToDTO);
    }
    
    public Chambre createChambre(List<MultipartFile> photos,Chambre chambre) throws IOException {
    	 for (MultipartFile photo : photos) {
             chambre.getPhotos().add(photo.getBytes());
         }
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

    public byte[] getPhoto(String fileName) throws IOException {
        Path filePath = Paths.get(uploadDir + fileName);
        return Files.readAllBytes(filePath);
    }
        
    public void deleteChambre(Long id) {
        chambreRepository.deleteById(id);
    }

    public List<Chambre> getAvailableChambres() {
        return chambreRepository.findByEtat(EtatChambre.DISPONIBLE);
    }
    
    private ChambreDTO mapToDTO(Chambre chambre) {
        ChambreDTO chambreDTO = new ChambreDTO();
        chambreDTO.setId(chambre.getId());
        chambreDTO.setNumeroChambre(chambre.getNumeroChambre());
        chambreDTO.setEtat(chambre.getEtat());
        chambreDTO.setType(chambre.getType());
        chambreDTO.setPrix(chambre.getPrix());
        chambreDTO.setDescription(chambre.getDescription());

        List<String> photoBase64List = chambre.getPhotos().stream()
                .map(photo -> Base64.getEncoder().encodeToString(photo))
                .collect(Collectors.toList());
        chambreDTO.setPhotos(photoBase64List);

        return chambreDTO;
    }
}