package com.krakenforce.app.controller;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.krakenforce.app.model.Payments;
import com.krakenforce.app.model.Transactions;
import com.krakenforce.app.model.Wallet;
import com.krakenforce.app.security.common.MessageResponse;
import com.krakenforce.app.service.PaymentService;
import com.krakenforce.app.service.TransactionsService;
import com.krakenforce.app.service.WalletService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/wallet")
public class WalletController {

	@Autowired
	WalletService walletService;
	
	@Autowired
	PaymentService paymentService;
	
	@Autowired
	TransactionsService transactionsService;
	
	@PutMapping
	public ResponseEntity<MessageResponse> updateWallet(@RequestParam("walletId") int walletId, @RequestParam("total") float total){
		try {
			Wallet wallet = walletService.updateWallet(walletId, total);
			
			Date date = new Date();
			Timestamp timestamp2 = new Timestamp(date.getTime());
			
			Payments payment = new Payments();
			payment.setWallet(wallet);
			payment.setTotalPrice(total);
			payment.setCreatedAt(timestamp2);
			payment.setProvider("PURCHASE");
			
			paymentService.add(payment);
			
			Transactions newTransaction = new Transactions();
			newTransaction.setComfirms(true);
			newTransaction.setPayment(payment);
			newTransaction.setDescription("Purchase: " + wallet.getUser().getUsername());
			newTransaction.setTotal(total);
			newTransaction.setStatus(true);
			newTransaction.setCreatedAt(timestamp2);
			transactionsService.add(newTransaction);
			
			transactionsService.add(newTransaction);
			
			return new ResponseEntity<MessageResponse>(new MessageResponse("Update wallet success"), new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Update wallet fail"), new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/top_up_user")
	public ResponseEntity<MessageResponse> topUpUserByAdmin(@RequestParam("walletId") int walletId, 
			@RequestParam("amount") float amount){
		try {
			Wallet wallet = walletService.getById(walletId);
			float newBalance = wallet.getBalance() + amount;
			wallet.setBalance(newBalance);
			walletService.add(wallet);
			
			Date date = new Date();
			Timestamp timestamp2 = new Timestamp(date.getTime());
			
			Payments payment = new Payments();
			payment.setWallet(wallet);
			payment.setTotalPrice(amount);
			payment.setCreatedAt(timestamp2);
			payment.setProvider("ADMINTOPUP");
			
			paymentService.add(payment);
			
			Transactions newTransaction = new Transactions();
			newTransaction.setComfirms(true);
			newTransaction.setPayment(payment);
			newTransaction.setDescription("Admin top up for : " + wallet.getUser().getUsername());
			newTransaction.setTotal(amount);
			newTransaction.setStatus(true);
			newTransaction.setCreatedAt(timestamp2);
			transactionsService.add(newTransaction);
			
			transactionsService.add(newTransaction);
			
			return new ResponseEntity<MessageResponse>(new MessageResponse("Top up success, check payment and transaction"), new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Top up fail"), new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
}
