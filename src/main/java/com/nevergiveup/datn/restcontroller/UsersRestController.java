package com.nevergiveup.datn.restcontroller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import com.nevergiveup.datn.entity.Users;
import com.nevergiveup.datn.service.UsersService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/users")
public class UsersRestController {
	@Autowired
	UsersService service;

	@Autowired
	HttpServletRequest req;
	
	@Autowired
	BCryptPasswordEncoder pe;

	@GetMapping
	public List<Users> getAllUser() {
		return service.findAllUsers();

	}

	@GetMapping("remoteuser")
	public Users get() {
		String user = req.getRemoteUser();
		if (user == null) {
			return null;
		} else {
			return service.findByUsername(user);
		}
	}

	@GetMapping("{username}")
	public Users getUserByUserName(@PathVariable("username") String username) {

		return service.findByUsername(username);
	}

	@PostMapping
	public Users create(@RequestBody Users users) {
		users.setPassword(pe.encode(users.getPassword()));
		return service.create(users);
	}

	@PutMapping("{username}")
	public Users update(@PathVariable("username") String username, @RequestBody Users users) {
		return service.update(users);
	}

	@DeleteMapping("{username}")
	public void delete(@PathVariable("username") String username) {
		service.delete(username);
	}
}
