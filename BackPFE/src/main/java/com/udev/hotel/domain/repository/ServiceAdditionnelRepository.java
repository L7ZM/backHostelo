package com.udev.hotel.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udev.hotel.domain.entity.ServiceAdditionnel;

public interface ServiceAdditionnelRepository extends JpaRepository<ServiceAdditionnel, Long>{
	List<ServiceAdditionnel> findAll();
}
