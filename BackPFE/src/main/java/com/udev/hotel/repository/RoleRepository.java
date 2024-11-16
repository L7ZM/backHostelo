package com.udev.hotel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udev.hotel.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String>{

    Optional<Role> findByName(String roleName);
}
