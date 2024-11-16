package com.udev.hotel.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udev.hotel.entity.Role;
import com.udev.hotel.entity.User;
import com.udev.hotel.repository.RoleRepository;
import com.udev.hotel.repository.UserRepository;
import com.udev.hotel.security.AuthoritiesConstants;
import com.udev.hotel.service.dto.UserDTO;

@Service
@Transactional
public class UserService {

	private final Logger log = LoggerFactory.getLogger(UserService.class);

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final RoleRepository roleRepository;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
	}

	public User createUser(UserDTO userDTO) {
		User user = new User();
		user.setEmail(userDTO.getEmail());
		user.setPrenom(userDTO.getPrenom());
		user.setNom(userDTO.getNom());
		user.setAdresse(userDTO.getAdresse());
		user.setDateNaissance(userDTO.getDateNaissance());
		user.setTelephone(userDTO.getTelephone());
		if (userDTO.getAuthorities() != null) {
			Set<Role> authorities = userDTO.getAuthorities().stream()
					.map(roleName -> roleRepository.findByName(roleName)
							.orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleName)))
					.collect(Collectors.toSet());
			user.setAuthorities(authorities);
		}

		String encryptedPassword = passwordEncoder.encode("1234");
		user.setpassword(encryptedPassword);
		userRepository.save(user);
		log.debug("Created Information for User: {}", user);
		return user;
	}

	public User registerUser(UserDTO userDTO, String password) {

		User newUser = new User();
		Role authority = roleRepository.findByName(AuthoritiesConstants.USER)
				.orElseThrow(() -> new IllegalArgumentException("Role not found: " + AuthoritiesConstants.USER));

		Set<Role> authorities = new HashSet<>();
		String encryptedPassword = passwordEncoder.encode(password);
		newUser.setEmail(userDTO.getEmail());
		newUser.setpassword(encryptedPassword);
		newUser.setPrenom(userDTO.getPrenom());
		newUser.setNom(userDTO.getNom());
		newUser.setAdresse(userDTO.getAdresse());
		newUser.setDateNaissance(userDTO.getDateNaissance());
		newUser.setTelephone(userDTO.getTelephone());
		newUser.setEmail(userDTO.getEmail());
		authorities.add(authority);
		newUser.setAuthorities(authorities);
		userRepository.save(newUser);
		log.debug("Created Information for User: {}", newUser);
		return newUser;
	}

	public void deleteUser(String email) {
		userRepository.findByEmail(email).ifPresent(user -> {
			userRepository.delete(user);
			log.debug("Deleted User: {}", user);
		});
	}

	/**
	 * @return a list of all the authorities
	 */
	public List<String> getAuthorities() {
		return roleRepository.findAll().stream().map(Role::getName).collect(Collectors.toList());
	}

}
