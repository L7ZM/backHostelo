package com.udev.hotel.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

	public Optional<User> loadUserByUsername(String username) {
		return userRepository.findByEmail(username);

	}
	
    /**
     * Update basic information (first name, last name, email ..) for the current user.
     *
     * @param prenom first name of user
     * @param nom last name of user
     * @param email email id of user
     * @param adresse language key
     * @param telephone of user
     * @param dateNaissance of user
     * 
     */
    public void updateUser(String prenom, String nom, String email,String password, String adresse, String telephone, LocalDate dateNaissance) {
		
    	String encryptedPassword = passwordEncoder.encode(password);
    	SecurityUtils.getCurrentUserLogin()
            .flatMap(userRepository::findByEmail)
            .ifPresent(user -> {
                user.setPrenom(prenom);
                user.setNom(nom);
                user.setEmail(email);
                user.setPassword(encryptedPassword);
                user.setAdresse(adresse);
                user.setTelephone(telephone);
                user.setDateNaissance(dateNaissance);
                log.debug("Changed Information for User: {}", user);
            });
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
	
	public void updatePassword(String newPassword) {
        User currentUser = getCurrentUser(); 
        log.info(currentUser.toString());
        if (currentUser.getPassword() != null) {
	        String encodedNewPassword = passwordEncoder.encode(newPassword);
	        log.debug(encodedNewPassword); 
	        currentUser.setPassword(encodedNewPassword);
	    } else {
	        throw new IllegalArgumentException("Password cannot be null");
	    }
        userRepository.save(currentUser);
    }
	
	private User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}


