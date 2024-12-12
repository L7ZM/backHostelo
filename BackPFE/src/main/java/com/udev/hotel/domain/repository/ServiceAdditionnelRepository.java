package com.udev.hotel.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udev.hotel.domain.entity.ServiceAdditionnel;

public interface ServiceAdditionnelRepository extends JpaRepository<ServiceAdditionnel, Long>{
	List<ServiceAdditionnel> findAll();
	ServiceAdditionnel findByNomService(String nomService);
}
