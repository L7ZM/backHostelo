package com.udev.hotel.service.dto;

import java.util.List;

import com.udev.hotel.config.constants.EtatChambre;
import com.udev.hotel.config.constants.TypeChambre;

import lombok.Data;

@Data
public class ChambreDTO {
    private Long id;
    private int numeroChambre;
    private EtatChambre etat;
    private TypeChambre type;
    private double prix;
    private String description;
    private List<String> photos;
    
}
