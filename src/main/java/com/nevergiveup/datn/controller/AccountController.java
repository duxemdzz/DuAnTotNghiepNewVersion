package com.nevergiveup.datn.controller;

import java.util.Date;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nevergiveup.datn.entity.Cart;
import com.nevergiveup.datn.entity.MailInfo;
import com.nevergiveup.datn.entity.Users;
import com.nevergiveup.datn.service.CartService;
import com.nevergiveup.datn.service.MailerService;
import com.nevergiveup.datn.service.UsersService;

@Controller
public class AccountController {
	@Autowired
	CartService cart;
	@Autowired
	UsersService usersService;
	@Autowired
	BCryptPasswordEncoder pe;
	@Autowired
	HttpServletRequest req;
	@Autowired
	CartService cartService;

	@Autowired
	MailerService mailer;

	@RequestMapping("/account/login")
	public String login(Model model) {
		model.addAttribute("message", "Vui lòng đăng nhập!");
		return "users/account/login/login";
	}

	@RequestMapping("/account/login/success")
	public String loginSuccess(Model model) {
		try {
			String user = req.getRemoteUser();
			Cart cartCheck = cartService.findCartByUsername(user);
			if (user == null) {
				return null;
			} else {
				if (cartCheck == null) {
					Users users = usersService.findByUsername(user);
					Cart cart = new Cart();
					cart.setUsers(users);
					cartService.create(cart);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		model.addAttribute("message", "Đăng nhập thành công!");
		return "redirect:/";
	}

	@RequestMapping("/account/login/error")
	public String loginError(Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập hoặc tài khoản chưa được kích hoạt!");
		return "users/account/login/login";
	}

	@RequestMapping("/account/logoff/success")
	public String logoffSuccess(Model m) {
		m.addAttribute("message", "Đăng Xuất Thành Công");
		return "redirect:/account/login";
	}

	@RequestMapping("/account/register")
	public String register() {
		return "users/account/register/register";
	}

	@GetMapping("/account/forgotpassword")
	public String forgotPassword() {
		return "users/account/forgotPassword/forgotpassword";
	}

	@PostMapping("/account/forgotpassword")
	public String forgot(Model model) throws MessagingException {
		try {
			String userid = req.getParameter("username");
			Users item = usersService.findByUsername(userid);
			String passwordRandom = (int) Math.floor(Math.random() * 1000000) + "";
			String email = req.getParameter("email");
			System.err.println(item.getEmail().equals(email));
			if (item.getUsername().equals(userid) && item.getEmail().equals(email)) {
				String to = email;
				if (item != null) {
					String subject = "Welcome!";
					String body = "Techno Shop xin chào! Mật khẩu của bạn là: " + "<b style='color: red'>"
							+ passwordRandom + "</b>" + "<br />"
							+ "<b>Lưu ý:</b> <u>Mật khẩu này được tạo tự động. Bạn cần thay đổi để tăng tính bảo mật!<u>";
					MailInfo mail = new MailInfo(to, subject, body);
					mailer.send(mail);
					model.addAttribute("message", "Mật khẩu đã được gửi về email của bạn!");
					item.setPassword(pe.encode(passwordRandom));
					usersService.update(item);
				}
			} else {
				model.addAttribute("message", "Tên đăng nhập hoặc email là không chính xác!");
			}
		} catch (Exception e) {
			model.addAttribute("message", "Tên đăng nhập không tồn tại!");
		}

		return "users/account/forgotPassword/forgotpassword";
	}

	@RequestMapping("/account/edit")
	public String edit() {
		return "/users/account/edit/edit";
	}

	@GetMapping("/account/repass")
	public String repass() {
		return "/users/account/change/re-pass";
	}

	@PostMapping("/account/repass")
	public String change1(Model model) {
		String username = req.getRemoteUser();
		Users item = usersService.findByUsername(username);
		String pw = req.getParameter("pw");
		String pw1 = req.getParameter("pw1");
		String pw2 = req.getParameter("pw2");
		if (pe.matches(pw, item.getPassword())) {
			if (pw1.equals(pw2)) {
				item.setPassword(pe.encode(pw1));
				usersService.update(item);
				model.addAttribute("message", "Thay đổi mật khẩu thành công!");
			} else {
				model.addAttribute("message", "Mật khẩu mới và xác nhận mật khẩu là không khớp!");
			}
		} else {
			model.addAttribute("message", "Mật khẩu hiện tại là không chính xác!");
		}
		return "/users/account/change/re-pass";
	}

	@RequestMapping("/account/unauthoried")
	public String unauthoried(Model model) {
		model.addAttribute("message", "Không có quyền truy xuất!");
		return "users/account/login/login";
	}

	@RequestMapping("/oauth2/login/success")
	public String success(OAuth2AuthenticationToken oauth2) {

		String email = oauth2.getPrincipal().getAttribute("email");
		String fullname = oauth2.getPrincipal().getAttribute("name");
		String photo = oauth2.getPrincipal().getAttribute("picture");
		String password = Long.toHexString(System.currentTimeMillis());
		Users users = new Users();
		users.setUsername(email);
		users.setPassword(pe.encode(password));
		users.setFirstName(fullname);
		users.setEmail(email);
		users.setPhoto(photo);
		users.setIsActive(true);
		users.setIsDisable(false);
		users.setCreateDate(new Date());

		System.out.println(users);

		if (usersService.findByIdExit(users.getUsername())) {
			System.err.println("Đã có rồi!");
			try {
				String user = req.getRemoteUser();
				Cart cartCheck = cartService.findCartByUsername(user);
				if (user == null) {
					return null;
				} else {
					if (cartCheck == null) {
						users = usersService.findByUsername(user);
						Cart cart = new Cart();
						cart.setUsers(users);
						cartService.create(cart);
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else {
			usersService.create(users);
		}

		usersService.loginFromOAuth2(oauth2);
		return "forward:/account/login/success";
	}

}
