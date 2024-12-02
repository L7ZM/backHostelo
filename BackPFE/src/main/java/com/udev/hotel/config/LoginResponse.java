package com.udev.hotel.config;

import java.util.List;

public class LoginResponse {
    private String token;

    private long expiresIn;
    
    private String username; // Add this
    private String email;    // Add this if needed
    private List<String> roles; // Add roles as a list of strings

    public String getToken() {
        return token;
    }

	public long getExpiresIn() {
		return expiresIn;
	}

	public LoginResponse setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        return this; 
    }

    public LoginResponse setToken(String token) {
        this.token = token;
        return this; 
    }

	public String getUsername() {
		return username;
	}

	public LoginResponse  setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public LoginResponse  setEmail(String email) {
		this.email = email;
		return this;
	}

	public List<String> getRoles() {
		return roles;
	}

	public LoginResponse  setRoles(List<String> roles) {
		this.roles = roles;
		return this;
	}
    
    

}