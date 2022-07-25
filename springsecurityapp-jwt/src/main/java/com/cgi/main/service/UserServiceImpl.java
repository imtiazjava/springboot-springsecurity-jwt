package com.cgi.main.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cgi.main.exception.UserNotFoundException;
import com.cgi.main.model.User;
import com.cgi.main.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder pwdEncoder;

	@Override
	public Integer saveUser(User user) {
		// Encode Password
		user.setPassword(pwdEncoder.encode(user.getPassword()));
		// save user
		return userRepository.save(user).getId();
	}

	@Override
	public Optional<User> findByUsername(String username) {
		// TODO Auto-generated method stub
		return this.userRepository.findByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> op = findByUsername(username);
		if (op.isEmpty()) {
			throw new UserNotFoundException("User Not Exits");
		}
		User user = op.get();

		return new org.springframework.security.core.userdetails.User(username, user.getPassword(),
				user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList()));

	}

}
