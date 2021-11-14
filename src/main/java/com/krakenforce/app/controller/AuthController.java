package com.krakenforce.app.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
import com.krakenforce.app.exception.TokenRefreshException;
import com.krakenforce.app.exception.UsersNotFoundException;
import com.krakenforce.app.model.RefreshToken;
import com.krakenforce.app.model.Roles;
import com.krakenforce.app.model.ShoppingCart;
import com.krakenforce.app.model.Users;
import com.krakenforce.app.model.Wallet;
import com.krakenforce.app.repository.RolesRepository;
import com.krakenforce.app.repository.UsersRepository;
import com.krakenforce.app.security.common.ForgotPasswordRequest;
import com.krakenforce.app.security.common.JwtResponse;
import com.krakenforce.app.security.common.JwtUtils;
import com.krakenforce.app.security.common.LoginRequest;
import com.krakenforce.app.security.common.MessageResponse;
import com.krakenforce.app.security.common.ResetPasswordRequest;
import com.krakenforce.app.security.common.SignUpRequest;
import com.krakenforce.app.security.common.TokenRefreshRequest;
import com.krakenforce.app.security.common.TokenRefreshResponse;
import com.krakenforce.app.security.services.UserDetailsImpl;
import com.krakenforce.app.service.RefreshTokenService;
import com.krakenforce.app.service.ShoppingCartService;
import com.krakenforce.app.service.UsersService;
import com.krakenforce.app.service.WalletService;

import net.bytebuddy.utility.RandomString;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	UsersService usersService;

	@Autowired
	RolesRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	RefreshTokenService refreshTokenService;

	@Autowired
	WalletService walletService;

	@Autowired
	ShoppingCartService shoppingCartService;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	JwtUtils jwtUtils;

	/**
	 * @author Toan ngo use to authenticate User when login
	 * @param loginRequest
	 * @return new JwtResponse
	 */
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		refreshTokenService.deleteByUserId(userDetails.getId());
		RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

		return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(),
				userDetails.getUsername(), userDetails.getEmail(), userDetails.getCartId(), userDetails.getWalletId(),
				userDetails.getWalletBalance(), userDetails.getAvatarImageUrl(), roles));

	}

	/**
	 * @author Toan ngo use to register User when login
	 * @param SignUpRequest
	 * @return new MessageResponse
	 */
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Validated @RequestBody SignUpRequest signUpRequest) {
		if (usersRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken"));
		}

		if (usersRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use"));
		}

		// create new user's account
		Users user = new Users(signUpRequest.getUsername(), signUpRequest.getEmail(),
				passwordEncoder.encode(signUpRequest.getPassword()));

		Set<String> strRoleSet = signUpRequest.getRole();
		Set<Roles> roles = new HashSet<>();

		if (strRoleSet == null) {
			Roles userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoleSet.forEach(role -> {
				switch (role) {
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
		addCartAndWallet(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully"));

	}

	// use to add cart and wallet to new user
	public void addCartAndWallet(Users user) {
		Wallet newWallet = new Wallet();
		newWallet.setBalance(0);
		newWallet.setStatus(true);
		newWallet.setUser(user);
		walletService.add(newWallet);

		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setStatus(true);
		shoppingCart.setUser(user);
		shoppingCartService.add(shoppingCart);
	}

	/**
	 * use to refresh token
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/refreshtoken")
	public ResponseEntity<?> refreshtoken(@RequestBody TokenRefreshRequest request) {
		String requestRefreshToken = request.getRefreshToken();

		return refreshTokenService.findByToken(requestRefreshToken).map(refreshTokenService::verifyExpiration)
				.map(RefreshToken::getUser).map(user -> {
					String token = jwtUtils.generateTokenFromUsername(user.getUsername());
					return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
				})
				.orElseThrow(() -> new TokenRefreshException(requestRefreshToken, "Refresh token is not in database!"));
	}

	/**
	 * use to process forgot password token
	 * 
	 * @return
	 */
	@PostMapping("/forgot_password")
	public ResponseEntity<MessageResponse> processForgotPassword(
			@RequestBody ForgotPasswordRequest forgotPasswordRequest) {
		String email = forgotPasswordRequest.getEmail();
		String token = RandomString.make(30);
		try {
			usersService.updateResetPasswordToken(token, email);
			String resetPasswordLink = "http://localhost:4000" + "/reset_password?token=" + token;
			sendEmail(email, resetPasswordLink);
			return new ResponseEntity<MessageResponse>(new MessageResponse("Sent email, check your email"),
					new HttpHeaders(), HttpStatus.OK);
		} catch (UsersNotFoundException e) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Sent email fail, user not found"),
					new HttpHeaders(), HttpStatus.BAD_REQUEST);
		} catch (UnsupportedEncodingException | MessagingException e) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Error while sending email"),
					new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * use to process reset password
	 * 
	 * @return
	 */
	@PostMapping("/reset_password")
	public ResponseEntity<?> processResetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
		Users user = usersService.getByResetPasswordToken(resetPasswordRequest.getToken());
		if (user == null) {
			throw new UsersNotFoundException("Not found user");
		} else {
			usersService.updatePassword(user, resetPasswordRequest.getPassword());
			return ResponseEntity.ok(null);
		}
	}

	public void sendEmail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException {

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("krakenshp@shopme.com", "Shop Support");
		helper.setTo(recipientEmail);

		String subject = "Here's the link to reset your password";

		String content = "<p>Hello,</p>" + "<p>You have requested to reset your password.</p>"
				+ "<p>Click the link below to change your password:</p>" + "<p><a href=\"" + link
				+ "\">Change my password</a></p>" + "<br>" + "<p>Ignore this email if you do remember your password, "
				+ "or you have not made the request.</p>";

		helper.setSubject(subject);

		helper.setText(content, true);

		mailSender.send(message);
	}

}
