package com.cgi.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.main.model.User;
import com.cgi.main.model.UserRequest;
import com.cgi.main.model.UserResponse;
import com.cgi.main.service.IUserService;
import com.cgi.main.util.JwtUtil;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private IUserService service;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil util;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveUser(@RequestBody User user) {
		Integer i = service.saveUser(user);
		return ResponseEntity.ok("User:" + i);
	}

	// 2. Validate user and generate token(login)
	@PostMapping("/loginuser")
	public ResponseEntity<UserResponse> loginUser(@RequestBody UserRequest request) {
		 
		System.err.println("userreq:"+request);
		System.out.println(request.getPassword()+"<------------");
		Authentication auth=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		System.out.println(auth.isAuthenticated()+"<===");
		String token=util.generateToken(request.getUsername());
		return ResponseEntity.ok(new UserResponse(token,"SUCCESS"));
	}

}
