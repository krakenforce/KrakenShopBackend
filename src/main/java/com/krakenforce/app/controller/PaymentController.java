package com.krakenforce.app.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.krakenforce.app.model.Payments;
import com.krakenforce.app.model.Transactions;
import com.krakenforce.app.security.common.MessageResponse;
import com.krakenforce.app.service.PaymentService;
import com.krakenforce.app.service.TransactionsService;

@CrossOrigin(origins ="*", maxAge = 3600)
@RestController
@RequestMapping("/api/payment")
public class PaymentController {
	
	@Autowired
	TransactionsService transactionsService;
	
	@Autowired
	PaymentService paymentService;
	
	// PAYMENT
	@PostMapping()
	public ResponseEntity<Payments> addPayment(@RequestBody Payments payment){
		try {
			paymentService.add(payment);
			return new ResponseEntity<Payments>(payment, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Payments>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{paymentId}")
	public ResponseEntity<MessageResponse> deletePayment(@PathVariable("paymentId") int paymentId){
		try {
			paymentService.delete(paymentId);
			return new ResponseEntity<MessageResponse>(new MessageResponse("Delete Success"), new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Delete fail"), new HttpHeaders(), HttpStatus.OK);
		}
	}
	
	@GetMapping("/{paymentId}")
	public ResponseEntity<Payments> getPaymentById(@PathVariable("paymentId") int paymentId){
		try {
			Payments payment = paymentService.getById(paymentId);
			return new ResponseEntity<Payments>(payment, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Payments>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping()
	public ResponseEntity<Map<String, Object>> getAllPayment(@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "0") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy){
		try {
			Map<String, Object> response = paymentService.getAll(pageNo, pageSize, sortBy);
			return new ResponseEntity<Map<String, Object>>(response, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Map<String, Object>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search_provider")
	public ResponseEntity<Map<String, Object>> getPaymentByProvider(@RequestParam("provider") String provider,
			@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "0") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy){
		try {
			Map<String, Object> payments = paymentService.getByProvider(provider, pageNo, pageSize, sortBy);
			return new ResponseEntity<Map<String, Object>>(payments, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Map<String, Object>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search_wallet")
	public ResponseEntity<Map<String, Object>> getPaymentByProvider(@RequestParam("walletId") int walletId,
			@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "0") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy){
		try {
			Map<String, Object> payments = paymentService.getByWalletId(walletId, pageNo, pageSize, sortBy);
			return new ResponseEntity<Map<String, Object>>(payments, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Map<String, Object>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search_time")
	public ResponseEntity<Map<String, Object>> getPaymentByTime(@RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime,
			@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "0") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy){
		try {
			Timestamp start = Timestamp.valueOf(startTime);
			Timestamp end = Timestamp.valueOf(endTime);
			Map<String, Object> payments = paymentService.getByTime(start, end, pageNo, pageSize, sortBy);
			return new ResponseEntity<Map<String, Object>>(payments, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Map<String, Object>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	
	
	// TRANSACTIONS
	@PostMapping("/transactions")
	public ResponseEntity<Transactions> addTransaction(@RequestBody Transactions transactions){
		try {
			transactionsService.add(transactions);
			return new ResponseEntity<Transactions>(transactions, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Transactions>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/transactions/{transactionId}")
	public ResponseEntity<MessageResponse> deleteTransaction(@PathVariable("transactionId") int transactionId){
		try {
			transactionsService.delete(transactionId);
			return new ResponseEntity<MessageResponse>(new MessageResponse("Delete success"), new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Delete fail"), new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/transactions/{transactionId}")
	public ResponseEntity<Transactions> getTransactionById(@PathVariable("transactionId") int transactionId){
		try {
			Transactions transactions =  transactionsService.getById(transactionId);
			return new ResponseEntity<Transactions>(transactions, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Transactions>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/transactions")
	public ResponseEntity<Map<String, Object>> getAllTransaction(@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "0") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy){
		try {
			Map<String, Object> transactions =  transactionsService.getAll(pageNo, pageSize, sortBy);
			return new ResponseEntity<Map<String, Object>>(transactions, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Map<String, Object>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/transactions/search_payment/{paymentId}")
	public ResponseEntity<List<Transactions>> getTransactionByPayment(@PathVariable("paymentId") int paymentId){
		try {
			List<Transactions> transactions =  transactionsService.getByPaymentId(paymentId);
			return new ResponseEntity<List<Transactions>>(transactions, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Transactions>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/transactions/search_wallet")
	public ResponseEntity<Map<String, Object>> getTransactionByWallet(@RequestParam("walletId") int walletId,
			@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "0") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy){
		try {
			Map<String,Object> transactions =  transactionsService.getByWallet(walletId, pageNo, pageSize, sortBy);
			return new ResponseEntity<Map<String,Object>>(transactions, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Map<String,Object>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	@GetMapping("/transactions/search_keyword")
	public ResponseEntity<Map<String, Object>> getTransactionByKeyword(@RequestParam("keyword") String keyword,
			@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "0") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy){
		try {
			Map<String,Object> transactions =  transactionsService.getByKeyword(keyword, pageNo, pageSize, sortBy);
			return new ResponseEntity<Map<String,Object>>(transactions, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Map<String,Object>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/transactions/search_time")
	public ResponseEntity<Map<String,Object>> getTransactionByKeyword(@RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime,
			@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "0") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy){
		try {
			Timestamp start = Timestamp.valueOf(startTime);
			Timestamp end = Timestamp.valueOf(endTime);
			Map<String,Object> transactions =  transactionsService.getByTime(start, end, pageNo, pageSize, sortBy);
			return new ResponseEntity<Map<String,Object>>(transactions, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Map<String,Object>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	
	
	
}
