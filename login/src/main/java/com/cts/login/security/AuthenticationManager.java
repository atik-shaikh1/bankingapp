package com.cts.login.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.cts.login.model.User;
import com.cts.login.utils.JwtUtil;

import reactor.core.publisher.Mono;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public Mono<Authentication> authenticate(Authentication authentication) {

		String authToken = authentication.getCredentials().toString();

		String username = "";
		try {
			if (authToken != null && authToken.startsWith("Bearer ")) {
				String jwt = authToken.substring(7);
				username = jwtUtil.getUserNameFromToken(jwt);
			}

		} catch (Exception e) {
			username = null;
			e.printStackTrace();
		}
		if (username != null && jwtUtil.validateToken(authToken)) {
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null,
					Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
			User user = new User();
			user.setUsername(username);
			List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
			SecurityContextHolder.getContext().setAuthentication(new AuthenticatedUser(username, authorities));
			return Mono.just(auth);
		} else {
			return Mono.empty();
		}
	}

}
