package com.udev.hotel.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udev.hotel.config.security.AuthoritiesConstants;
import com.udev.hotel.config.security.SecurityUtils;
import com.udev.hotel.domain.entity.Role;
import com.udev.hotel.domain.entity.User;
import com.udev.hotel.domain.repository.RoleRepository;
import com.udev.hotel.domain.repository.UserRepository;
import com.udev.hotel.service.dto.UserDTO;

@Service
@Transactional
public class UserService {

	private final Logger log = LoggerFactory.getLogger(UserService.class);

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
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
			user.setRoles(authorities);
		}

		String encryptedPassword = passwordEncoder.encode(userDTO.getPassword());
		user.setPassword(encryptedPassword);
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
		newUser.setPassword(encryptedPassword);
		newUser.setPrenom(userDTO.getPrenom());
		newUser.setNom(userDTO.getNom());
		newUser.setAdresse(userDTO.getAdresse());
		newUser.setDateNaissance(userDTO.getDateNaissance());
		newUser.setTelephone(userDTO.getTelephone());
		newUser.setEmail(userDTO.getEmail());
		authorities.add(authority);
		newUser.setRoles(authorities);
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

	public Optional<User> loadUserByUsername(String username) {
		return userRepository.findByEmail(username);

	}

	public List<User> allUsers() {
		List<User> users = new ArrayList<>();

		userRepository.findAll().forEach(users::add);

		return users;
	}

	/**
	 * Update all information for a specific user, and return the modified user.
	 *
	 * @param userDTO user to update
	 * @return updated user
	 */
	public Optional<UserDTO> updateUser(UserDTO userDTO) {
	    return userRepository.findById(userDTO.getId())
	        .map(user -> {
	            user.setEmail(userDTO.getEmail().toLowerCase());
	            user.setPrenom(userDTO.getPrenom());
	            user.setNom(userDTO.getNom());
	            user.setAdresse(userDTO.getAdresse());
	            user.setTelephone(userDTO.getTelephone());
	            user.setDateNaissance(userDTO.getDateNaissance());
	            Set<Role> authorities = userDTO.getAuthorities().stream()
	                .map(roleRepository::findByName)
	                .filter(Optional::isPresent)
	                .map(Optional::get)
	                .collect(Collectors.toSet());
	            user.setRoles(authorities);
	            userRepository.save(user);
	            log.debug("Updated User: {}", user);
	            return new UserDTO(user);
	        });
	}
	
    /**
     * Update basic information (first name, last name, email, language) for the current user.
     *
     * @param prenom first name of user
     * @param nom last name of user
     * @param email email id of user
     * @param adresse language key
     * @param telephone of user
     * @param dateNaissance of user
     * @param pointFidelite of user
     */
    public void updateUser(String prenom, String nom, String email, String adresse, String telephone, LocalDate dateNaissance ,int pointFidelite) {
        SecurityUtils.getCurrentUserLogin()
            .flatMap(userRepository::findByEmail)
            .ifPresent(user -> {
                user.setPrenom(prenom);
                user.setNom(nom);
                user.setEmail(email);
                user.setAdresse(adresse);
                user.setTelephone(telephone);
                user.setDateNaissance(dateNaissance);
                user.setPointsFidelite(pointFidelite);
                log.debug("Changed Information for User: {}", user);
            });
    }
}


