package com.udev.hotel.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udev.hotel.config.security.AuthoritiesConstants;
import com.udev.hotel.domain.entity.Chambre;
import com.udev.hotel.service.ChambreService;
import com.udev.hotel.service.dto.ChambreDTO;


@RestController
@RequestMapping("/api/chambres")
public class ChambreController {

	private final Logger log = LoggerFactory.getLogger(ChambreService.class);

    @Autowired
    private ChambreService chambreService;

    @GetMapping
    public ResponseEntity<List<Chambre>> getAllChambres() {
        List<Chambre> chambres = chambreService.getAllChambres();
        return new ResponseEntity<>(chambres, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChambreDTO> getChambreById(@PathVariable Long id) {
        return chambreService.getChambreById(id)
                .map(chambre -> new ResponseEntity<>(new ChambreDTO(chambre), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<Chambre> createChambre(@RequestPart("chambre") String chambreJson,
            @RequestPart("photo")  List<MultipartFile> photos) {
    	 try {
             ObjectMapper objectMapper = new ObjectMapper();
             Chambre chambre = objectMapper.readValue(chambreJson, Chambre.class);

             log.info("Chambre JSON: {}", chambreJson);
             for (MultipartFile photo : photos) {
                 log.info("Photo name: {}", photo.getOriginalFilename());
             }

             Chambre savedChambre = chambreService.createChambre(photos, chambre);
             return ResponseEntity.status(HttpStatus.CREATED).body(savedChambre);
         } catch (IllegalArgumentException e) {
             log.error("Validation error: {}", e.getMessage());
             return ResponseEntity.badRequest().body(null);
         } catch (IOException e) {
             log.error("Error saving chambre: {}", e.getMessage());
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
         }
    }
    
    @GetMapping("/photo/{fileName}")
    public ResponseEntity<byte[]> getPhoto(@PathVariable String fileName) {
        try {
            byte[] photoData = chambreService.getPhoto(fileName);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) 
                    .body(photoData);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<Chambre> updateChambre(@PathVariable Long id, @RequestBody Chambre updatedChambre) {
        try {
            Chambre updated = chambreService.updateChambre(id, updatedChambre);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<Void> deleteChambre(@PathVariable Long id) {
        chambreService.deleteChambre(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<Chambre>> getAvailableChambres() {
        List<Chambre> chambres = chambreService.getAvailableChambres();
        return new ResponseEntity<>(chambres, HttpStatus.OK);
    }
}