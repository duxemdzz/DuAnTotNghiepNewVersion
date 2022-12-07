package com.nevergiveup.datn.service;

import java.util.List;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import com.nevergiveup.datn.entity.Users;


public interface UsersService {
	public List <Users> findAllUsers();
	public Users create(Users users);
	public Users update(Users users);
	public void delete(String id);
	public Users findByUsername(String username);	
	
	void loginFromOAuth2(OAuth2AuthenticationToken oauth2);

	Boolean findByIdExit(String user);
}
