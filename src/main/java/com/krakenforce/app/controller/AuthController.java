package com.krakenforce.app.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.management.relation.RoleResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakenforce.app.enums.ERole;
import com.krakenforce.app.model.Roles;
import com.krakenforce.app.model.Users;
import com.krakenforce.app.repository.RolesRepository;
import com.krakenforce.app.repository.UsersRepository;
import com.krakenforce.app.security.common.JwtResponse;
import com.krakenforce.app.security.common.JwtUtils;
import com.krakenforce.app.security.common.LoginRequest;
import com.krakenforce.app.security.common.MessageResponse;
import com.krakenforce.app.security.common.SignUpRequest;
import com.krakenforce.app.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	RolesRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtUtils jwtUtils;
	
	
	/**
	 * @author Toan ngo
	 * use to authenticate User when login
	 * @param loginRequest
	 * @return new JwtResponse
	 */
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest){
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername()
						, loginRequest.getPassword())
				);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(new JwtResponse(jwt, 
										userDetails.getId(), 
										userDetails.getUsername(), 
										userDetails.getEmail(),
										roles));
	}
	
	/**
	 * @author Toan ngo
	 * use to register User when login
	 * @param SignUpRequest
	 * @return new MessageResponse
	 */
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Validated @RequestBody SignUpRequest signUpRequest){
		if(usersRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken"));
		}
		
		if(usersRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use"));
		}
		
		//create new user's account
		Users user = new Users(signUpRequest.getUsername(),
								signUpRequest.getEmail(),
								passwordEncoder.encode(signUpRequest.getPassword()));
		
		Set<String> strRoleSet = signUpRequest.getRole();
		Set<Roles> roles = new HashSet<>();
		
		if(strRoleSet == null) {
			Roles userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		}else {
			strRoleSet.forEach(role -> {
				switch(role) {
				case "admin":
					Roles adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
									.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
					roles.add(adminRole);
				case "mod":
					Roles modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
									.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
					roles.add(modRole);
				default:
					Roles userRole = roleRepository.findByName(ERole.ROLE_USER)
									.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
					roles.add(userRole);
				}
			});
		}
		user.setRoleSet(roles);
		usersRepository.save(user);
		
		return ResponseEntity.ok(new MessageResponse("User registered successfully"));
		
	}
	
	
	
	
	
	
}
