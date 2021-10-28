package com.krakenforce.app.controller;

import java.time.Instant;
import java.util.List;

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
	public ResponseEntity<List<Payments>> getAllPayment(@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "0") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy){
		try {
			List<Payments> payments = paymentService.getAll(pageNo, pageSize, sortBy);
			return new ResponseEntity<List<Payments>>(payments, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Payments>>(null, new HttpHeaders(), HttpStatus.OK);
		}
	}
	
	@GetMapping("/search_provider")
	public ResponseEntity<List<Payments>> getPaymentByProvider(@RequestParam("provider") String provider,
			@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "0") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy){
		try {
			List<Payments> payments = paymentService.getByProvider(provider, pageNo, pageSize, sortBy);
			return new ResponseEntity<List<Payments>>(payments, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Payments>>(null, new HttpHeaders(), HttpStatus.OK);
		}
	}
	
	@GetMapping("/search_time")
	public ResponseEntity<List<Payments>> getPaymentByTime(@RequestParam("startTime") Instant startTime,
			@RequestParam("endTime") Instant endTime,
			@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "0") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy){
		try {
			List<Payments> payments = paymentService.getByTime(startTime, endTime, pageNo, pageSize, sortBy);
			return new ResponseEntity<List<Payments>>(payments, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Payments>>(null, new HttpHeaders(), HttpStatus.OK);
		}
	}
	
	
	
	// TRANSACTIONS
	@PostMapping("/transactions")
	public ResponseEntity<Transactions> addTransaction(@RequestBody Transactions transactions){
		try {
			transactionsService.add(transactions);
			return new ResponseEntity<Transactions>(transactions, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Transactions>(null, new HttpHeaders(), HttpStatus.OK);
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
			return new ResponseEntity<Transactions>(null, new HttpHeaders(), HttpStatus.OK);
		}
	}
	
	@GetMapping("/transactions")
	public ResponseEntity<List<Transactions>> getAllTransaction(@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "0") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy){
		try {
			List<Transactions> transactions =  transactionsService.getAll(pageNo, pageSize, sortBy);
			return new ResponseEntity<List<Transactions>>(transactions, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Transactions>>(null, new HttpHeaders(), HttpStatus.OK);
		}
	}
	
	@GetMapping("/transactions/search_payment/{paymentId}")
	public ResponseEntity<List<Transactions>> getTransactionByPayment(@PathVariable("paymentId") int paymentId){
		try {
			List<Transactions> transactions =  transactionsService.getByPaymentId(paymentId);
			return new ResponseEntity<List<Transactions>>(transactions, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Transactions>>(null, new HttpHeaders(), HttpStatus.OK);
		}
	}
	
	@GetMapping("/transactions/search_keyword")
	public ResponseEntity<List<Transactions>> getTransactionByKeyword(@RequestParam("keyword") String keyword,
			@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "0") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy){
		try {
			List<Transactions> transactions =  transactionsService.getByKeyword(keyword, pageNo, pageSize, sortBy);
			return new ResponseEntity<List<Transactions>>(transactions, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Transactions>>(null, new HttpHeaders(), HttpStatus.OK);
		}
	}
	
	@GetMapping("/transactions/search_time")
	public ResponseEntity<List<Transactions>> getTransactionByKeyword(@RequestParam("startTime") Instant startTime,
			@RequestParam("endTime") Instant endTime,
			@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "0") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy){
		try {
			List<Transactions> transactions =  transactionsService.getByTime(startTime, endTime, pageNo, pageSize, sortBy);
			return new ResponseEntity<List<Transactions>>(transactions, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Transactions>>(null, new HttpHeaders(), HttpStatus.OK);
		}
	}
	
	
	
	
	
	
}
