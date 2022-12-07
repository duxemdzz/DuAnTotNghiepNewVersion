package com.nevergiveup.datn.controller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nevergiveup.datn.entity.MailInfo;
import com.nevergiveup.datn.entity.Users;
import com.nevergiveup.datn.service.MailerService;
import com.nevergiveup.datn.service.UsersService;

@Controller
public class MailerController {
	@Autowired
	UsersService service;

	@Autowired
	MailerService mailer;

	@Autowired
	HttpServletRequest request;

	@RequestMapping("/mailer/{username}")
	public String get(@PathVariable("username") String username, HttpServletRequest request) {

		Users users = service.findByUsername(username);
		String to = users.getEmail();
		String subject = "Welcome!";
		String url = request.getRequestURL().toString().replace("mailer", "activate");
		String body = "Techno Shop xin chào! Vui lòng nhấn vào <a href='" + url
				+ "'>Activate</a> để kích hoạt tài khoản.";
		MailInfo mail = new MailInfo(to, subject, body);
		try {
			mailer.send(mail);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return "redirect:/";
	}

	@GetMapping("/activate/{username}")
	public String activate(Model model, @PathVariable("username") String username) {
		Users users = service.findByUsername(username);
		users.setIsActive(true);
		service.update(users);
		return "redirect:/account/login";
	}
}
