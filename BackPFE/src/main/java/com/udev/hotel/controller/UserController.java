package com.udev.hotel.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.http.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.udev.hotel.config.constants.Constants;
import com.udev.hotel.config.security.AuthoritiesConstants;
import com.udev.hotel.controller.exceptionHandler.BadRequestAlertException;
import com.udev.hotel.controller.exceptionHandler.EmailAlreadyUsedException;
import com.udev.hotel.controller.exceptionHandler.util.HeaderUtil;
import com.udev.hotel.domain.entity.User;
import com.udev.hotel.domain.repository.UserRepository;
import com.udev.hotel.service.UserService;
import com.udev.hotel.service.dto.UserDTO;

import io.micrometer.core.annotation.Timed;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

	private final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {

		this.userService = userService;
	}

	@PutMapping("/update")
	@Timed
	public ResponseEntity<Void> updateUser(@RequestBody UserDTO updateUserDto) {
		log.debug("REST request to update user information: {}", updateUserDto);

		userService.updateUser(updateUserDto.getPrenom(), updateUserDto.getNom(), updateUserDto.getEmail(),
				updateUserDto.getPassword(), updateUserDto.getAdresse(), updateUserDto.getTelephone(),
				updateUserDto.getDateNaissance());

		return ResponseEntity.ok().headers(HeaderUtil
				.createAlert("A user is updated with identifier " + updateUserDto.getEmail(), updateUserDto.getEmail()))
				.build();
	}
}
