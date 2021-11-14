package com.krakenforce.app.controller;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.krakenforce.app.dtos.UserFeedbackDtos;
import com.krakenforce.app.dtos.UserLogDtos;
import com.krakenforce.app.dtos.UserUpdateModel;
import com.krakenforce.app.dtos.UsersDtos;
import com.krakenforce.app.enums.ERole;
import com.krakenforce.app.exception.UsersNotFoundException;
import com.krakenforce.app.model.FeedbackType;
import com.krakenforce.app.model.UserFeedback;
import com.krakenforce.app.model.UserLog;
import com.krakenforce.app.model.Users;
import com.krakenforce.app.repository.UsersRepository;
import com.krakenforce.app.security.common.MessageResponse;
import com.krakenforce.app.service.FeedbackTypeService;
import com.krakenforce.app.service.FileStorageService;
import com.krakenforce.app.service.ProductCommentService;
import com.krakenforce.app.service.UserFeedbackService;
import com.krakenforce.app.service.UserLogService;
import com.krakenforce.app.service.UsersService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/user/")
public class UserController {

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	UsersService usersService;

	@Autowired
	UserLogService userLogService;

	@Autowired
	FileStorageService fileStorageService;

	@Autowired
	UserFeedbackService userFeedbackService;

	@Autowired
	FeedbackTypeService feedbackTypeService;

	@Autowired
	ProductCommentService productCommentService;

	@Autowired
	private JavaMailSender mailSender;

	@GetMapping()
	// @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String, Object>> getAllUser(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "userId") String sortBy) {
		try {
			Map<String, Object> response = usersService.getAllUser(pageNo, pageSize, sortBy);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(null, new HttpHeaders(), HttpStatus.FORBIDDEN);
		}

	}

	@GetMapping("/search-username")
	public ResponseEntity<Map<String, Object>> getUserByUsername(@RequestParam("username") String username,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "user_id") String sortBy) {
		Map<String, Object> response = usersService.getUserByUsername(username, pageNo, pageSize, sortBy);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/search-role")
	public ResponseEntity<Map<String, Object>> getUserByRole(@RequestParam("keyword") String keyword,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "userId") String sortBy) {
		ERole role = ERole.valueOf(keyword);
		Map<String, Object> response = usersService.getUserByRole(role, pageNo, pageSize, sortBy);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{userId}")
	// @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<UsersDtos> getUserById(@PathVariable("userId") int userId) {
		Users user = usersRepository.findById(userId).orElse(null);
		if (user != null) {
			UsersDtos dtos = new UsersDtos();
			dtos.setUserId(userId);
			dtos.setUsername(user.getUsername());
			dtos.setEmail(user.getEmail());
			dtos.setFirstName(user.getFirstName());
			dtos.setLastName(user.getLastName());
			dtos.setPhone(user.getPhone());
			dtos.setIdentityNumber(user.getIdentityNumber());
			dtos.setGender(user.getGender());
			dtos.setAvatarImageUrl(user.getAvatarImageUrl());
			dtos.setAddress(user.getAddress());
			dtos.setJob(user.getJob());
			dtos.setMarriageStatus(user.isMarriageStatus());
			dtos.setRegisteredAt(user.getRegisteredAt());
			dtos.setLastLogin(user.getLastLogin());
			dtos.setStatus(user.isStatus());
			dtos.setResetPasswordToken(user.getResetPasswordToken());
			if(user.getUserVipClass() != null) {
				dtos.setVipClassId(user.getUserVipClass().getId());
				dtos.setVipClassName(user.getUserVipClass().getClassName());
				dtos.setDiscountPercentage(user.getUserVipClass().getDiscountPercentage());
			}
			
			return ResponseEntity.ok(dtos);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@PutMapping("/update_password")
	public ResponseEntity<MessageResponse> updateUserPassword(@RequestParam("userId") int userId,
			@RequestParam("password") String password) {
		try {
			Users user = usersService.getById(userId);
			usersService.updatePassword(user, password);
			return new ResponseEntity<MessageResponse>(new MessageResponse("Change password success"),
					new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Change password fail"), new HttpHeaders(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	// @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> updateUserInfo(
			@RequestPart(value = "userUpdateModel", required = true) UserUpdateModel user,
			@RequestPart(value = "avatar", required = false) MultipartFile avatar) {

		try {
			Users selectedUser = usersRepository.findById(user.getUserId()).orElse(null);		
			if (avatar != null) {
				String fileUri = getImagePath(avatar);
				selectedUser.setAvatarImageUrl(fileUri);
			}
			selectedUser.setFirstName(user.getFirstName());
			selectedUser.setLastName(user.getLastName());
			selectedUser.setEmail(user.getEmail());
			selectedUser.setPhone(user.getPhone());
			selectedUser.setIdentityNumber(user.getIdentityNumber());
			selectedUser.setGender(user.getGender());
			selectedUser.setMarriageStatus(user.isMarriage());
			selectedUser.setAddress(user.getAddress());
			selectedUser.setJob(user.getJob());
			usersRepository.save(selectedUser);
			return ResponseEntity.ok(new MessageResponse("update success"));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<MessageResponse>(new MessageResponse("update fail"), new HttpHeaders(),
					HttpStatus.BAD_REQUEST);
		}

	}

	/* use to get Image path when upload */
	public String getImagePath(MultipartFile file) {
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/files/").path(fileName)
				.toUriString();
		return fileDownloadUri;
	}

	@DeleteMapping()
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteUser(@RequestParam("userId") int userId) {
		usersRepository.deleteById(userId);
		return ResponseEntity.ok(new MessageResponse("Delete user successfully"));
	}

	// =============================================================================================//
	// USER LOG MODULE

	@GetMapping("/log")
	@PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
	public ResponseEntity<List<UserLogDtos>> getAllUserlog(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy) {
		List<UserLogDtos> dtoList = new ArrayList<UserLogDtos>();
		try {
			List<UserLog> list = userLogService.getAllUserLog(pageNo, pageSize, sortBy);
			for (UserLog log : list) {
				UserLogDtos dto = new UserLogDtos(log.getId(), log.getUser().getUserId(), log.getCreatedAt(),
						log.getEventDetail());
				dtoList.add(dto);
			}
			return new ResponseEntity<List<UserLogDtos>>(dtoList, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<UserLogDtos>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/log/search/user")
	@PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
	public ResponseEntity<List<UserLogDtos>> getLogByUser(@RequestParam("userId") int userId,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy) {

		List<UserLogDtos> dtoList = new ArrayList<UserLogDtos>();
		try {
			List<UserLog> list = userLogService.getLogByUserId(userId, pageNo, pageSize, sortBy);
			for (UserLog log : list) {
				UserLogDtos dto = new UserLogDtos(log.getId(), log.getUser().getUserId(), log.getCreatedAt(),
						log.getEventDetail());
				dtoList.add(dto);
			}
			return new ResponseEntity<List<UserLogDtos>>(dtoList, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<UserLogDtos>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/log/search/time")
	@PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
	public ResponseEntity<List<UserLogDtos>> getLogByTime(@RequestParam("time1") Instant time1,
			@RequestParam("time2") Instant time2, @RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy) {

		List<UserLogDtos> dtoList = new ArrayList<UserLogDtos>();
		try {
			List<UserLog> list = userLogService.getLogByTime(time1, time2, pageNo, pageSize, sortBy);
			for (UserLog log : list) {
				UserLogDtos dto = new UserLogDtos(log.getId(), log.getUser().getUserId(), log.getCreatedAt(),
						log.getEventDetail());
				dtoList.add(dto);
			}
			return new ResponseEntity<List<UserLogDtos>>(dtoList, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<UserLogDtos>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/log/search/keyword")
	@PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
	public ResponseEntity<List<UserLogDtos>> getLogByKeyword(@RequestParam("keyword") String keyword,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy) {
		List<UserLogDtos> dtoList = new ArrayList<UserLogDtos>();
		try {
			List<UserLog> list = userLogService.getLogByKeyword(keyword, pageNo, pageSize, sortBy);
			for (UserLog log : list) {
				UserLogDtos dto = new UserLogDtos(log.getId(), log.getUser().getUserId(), log.getCreatedAt(),
						log.getEventDetail());
				dtoList.add(dto);
			}
			return new ResponseEntity<List<UserLogDtos>>(dtoList, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<UserLogDtos>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/log/search/usertime")
	@PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
	public ResponseEntity<List<UserLog>> getLogByUserAndTime(@RequestParam("userId") int userId,
			@RequestParam("create_at") Instant createAt, @RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy) {
		try {
			List<UserLog> list = userLogService.getLogByUserAndTime(userId, createAt, pageNo, pageSize, sortBy);
			return new ResponseEntity<List<UserLog>>(list, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<UserLog>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/log")
	@PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('USER')")
	public ResponseEntity<UserLog> addUserLog(@RequestBody UserLog userlog) {
		UserLog userLog = userLogService.save(userlog);
		return ResponseEntity.ok(userLog);
	}

	@DeleteMapping("/log/{logId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> deleteUserLog(@PathVariable("logId") int logId) {
		userLogService.delete(logId);
		return ResponseEntity.ok(new MessageResponse("Delete user log successfully"));
	}

	// ================================================================================================
	// USER FEEDBACK MODULE
	/**
	 * @category USER FEEDBACK MODULE
	 */

	@PostMapping("/feedback")
	public ResponseEntity<UserFeedback> addUserFeedback(@RequestBody UserFeedback userFeedback,
			@RequestParam("feedbackType") String feedbackTypeStr, @RequestParam("userId") int userId) {
		try {
			FeedbackType feedbackType = feedbackTypeService.getByName(feedbackTypeStr);
			Users user = usersService.getById(userId);

			userFeedback.setUser(user);
			userFeedback.setFeedbackType(feedbackType);
			Timestamp nowMoment = Timestamp.from(Instant.now());
			userFeedback.setDateTime(nowMoment);
			userFeedbackService.add(userFeedback);
			return ResponseEntity.ok(userFeedback);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(null);
		}

	}

	@DeleteMapping("/feedback/{feedbackId}")
	public ResponseEntity<MessageResponse> deleteUserFeedback(@PathVariable("feedbackId") int feedbackId) {
		userFeedbackService.delete(feedbackId);
		return ResponseEntity.ok(new MessageResponse("Delete user feedback successfully"));
	}

	@GetMapping("/feedback")
	public ResponseEntity<Map<String, Object>> getAllUserFeedback(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy) {
		try {
			Map<String, Object> response = userFeedbackService.getAllUserFeedback(pageNo, pageSize, sortBy);
			return new ResponseEntity<Map<String, Object>>(response, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Map<String, Object>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/feedback/search/user")
	public ResponseEntity<List<UserFeedbackDtos>> getUserFeedbackByUser(@RequestParam("userId") int userId,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy) {
		List<UserFeedbackDtos> dtoList = new ArrayList<UserFeedbackDtos>();
		try {
			List<UserFeedback> list = userFeedbackService.getFeedbackByUser(userId, pageNo, pageSize, sortBy);
			for (UserFeedback item : list) {
				UserFeedbackDtos dtos = new UserFeedbackDtos(item.getId(), item.getUser().getUserId(),
						item.getDateTime(), item.getFeedbackType().getId(), item.getFeedbackType().getName(),
						item.getDetail());
				dtoList.add(dtos);
			}
			return new ResponseEntity<List<UserFeedbackDtos>>(dtoList, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<UserFeedbackDtos>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/feedback/search/time")
	public ResponseEntity<Map<String, Object>> getUserFeedbackByTime(@RequestParam("startTime") String time1,
			@RequestParam("endTime") String time2, @RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy) {
		try {
			Timestamp start = Timestamp.valueOf(time1);
			Timestamp end = Timestamp.valueOf(time2);
			Map<String, Object> list = userFeedbackService.getFeedbackByTime(start, end, pageNo, pageSize, sortBy);
			return new ResponseEntity<Map<String, Object>>(list, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Map<String, Object>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/feedback/reply")
	public ResponseEntity<MessageResponse> sendFeedbackReply(@RequestParam("detail") String detail,
			@RequestParam("recipientEmail") String recipientEmail) {
		try {
			sendEmail(recipientEmail, detail);
			return new ResponseEntity<MessageResponse>(new MessageResponse("Sent Email"), new HttpHeaders(),
					HttpStatus.OK);
		} catch (UsersNotFoundException e) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Sent email fail, user not found"),
					new HttpHeaders(), HttpStatus.BAD_REQUEST);
		} catch (UnsupportedEncodingException | MessagingException e) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Error while sending email"),
					new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

	public void sendEmail(String recipientEmail, String detail)
			throws MessagingException, UnsupportedEncodingException {

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("krakenshp@shopme.com", "Shop Support");
		helper.setTo(recipientEmail);

		String subject = "Thank you for giving us feedback";

		String content = "<p>Hello,</p>" + "<p>You have sent us feedback.</p>" + "<p>This is our answer:</p>" + "<p>"
				+ detail + "<p>"
				+ "<p><strong>Thank you for using our service. We look forward to serving you again in the future. Dear</strong><p>";

		helper.setSubject(subject);

		helper.setText(content, true);

		mailSender.send(message);
	}

	// ==============================================================================================================
	// USER COMMENT
	/**
	 * @category USER COMMENT
	 */

	@GetMapping("/comment/{userId}")
	public ResponseEntity<Map<String, Object>> getCommentByProduct(@PathVariable("userId") int userId,
			@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "user_id") String sortBy) {
		try {
			Map<String, Object> list = productCommentService.getCommentByUser(userId, pageNo, pageSize, sortBy);
			return new ResponseEntity<Map<String, Object>>(list, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Map<String, Object>>(null, new HttpHeaders(), HttpStatus.OK);
		}
	}

}
