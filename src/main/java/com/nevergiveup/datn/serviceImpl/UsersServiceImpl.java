package com.nevergiveup.datn.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import com.nevergiveup.datn.dao.AuthorityDao;
import com.nevergiveup.datn.dao.CartDao;
import com.nevergiveup.datn.dao.RoleDao;
import com.nevergiveup.datn.dao.UsersDao;
import com.nevergiveup.datn.entity.Authority;
import com.nevergiveup.datn.entity.Cart;
import com.nevergiveup.datn.entity.Users;
import com.nevergiveup.datn.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {
	@Autowired
	UsersDao dao;
	@Autowired
	RoleDao rdao;
	@Autowired
	AuthorityDao authdao;
	@Autowired
	CartDao cartdao;

	@Override
	public List<Users> findAllUsers() {
		return dao.findAll();
	}

	@Override
	public Users create(Users user) {
		Users users = dao.saveAndFlush(user);
		Authority auth = new Authority();
		auth.setUsers(users);
		auth.setRole(rdao.findById("CUST").get());
		authdao.saveAndFlush(auth);
		Cart cart = new Cart();
		cart.setUsers(users);
		cartdao.saveAndFlush(cart);
		return users;
	}

	@Override
	public Users update(Users user) {
		return dao.saveAndFlush(user);
	}

	@Override
	public void delete(String username) {
		dao.deleteById(username);
	}

	@Override
	public Users findByUsername(String username) {
		return dao.findByUsername(username);
	}

	@Autowired
	BCryptPasswordEncoder pe;

	@Override
	public void loginFromOAuth2(OAuth2AuthenticationToken oauth2) {
		String email = oauth2.getPrincipal().getAttribute("email");
		String password = Long.toHexString(System.currentTimeMillis());

		UserDetails user = User.withUsername(email).password(pe.encode(password)).roles("User").build();

		Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	@Override
	public Boolean findByIdExit(String user) {
		return dao.existsById(user);
	}

}
