package com.krakenforce.app.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.krakenforce.app.model.Payments;
import com.krakenforce.app.model.Transactions;
import com.krakenforce.app.model.Wallet;
import com.krakenforce.app.security.common.MessageResponse;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

@Service
public class PaypalClient {
	
	
	String clientId = "AQowCGCbK10CopW5nu_8eMJ3w1hMIn39yF-Goa2XlCRGiK9V22Bbkqx1GfbhGWo5_nhJyWAAC0J0j4o5";
	String clientSecret = "EHFwpFe2cVe0KkZh61LOBoKemaBNugsyQ7V5Yni1ymrB5RMJDewjWaAVRHAYH7hY64lkodpgzs-by9x-";
	
	@Autowired
	WalletService walletService;
	
	@Autowired
	PaymentService paymentService;
	
	@Autowired
	TransactionsService transactionsService;
	
	public Map<String, Object> createPayment(String sum){
		
		
		//Create paypal payment
	    Map<String, Object> response = new HashMap<String, Object>();
	    Amount amount = new Amount();
	    amount.setCurrency("USD");
	    amount.setTotal(sum);
	    Transaction transaction = new Transaction();
	    transaction.setAmount(amount);
	    List<Transaction> transactions = new ArrayList<Transaction>();
	    transactions.add(transaction);

	    Payer payer = new Payer();
	    payer.setPaymentMethod("paypal");

	    Payment payment = new Payment();
	    payment.setIntent("sale");
	    payment.setPayer(payer);
	    payment.setTransactions(transactions);

	    RedirectUrls redirectUrls = new RedirectUrls();
	    redirectUrls.setCancelUrl("http://localhost:4000/cancel");
	    redirectUrls.setReturnUrl("http://localhost:4000/redirect");
	    payment.setRedirectUrls(redirectUrls);
	    Payment createdPayment;
	    try {
	        String redirectUrl = "";
	        APIContext context = new APIContext(clientId, clientSecret, "sandbox");
	        createdPayment = payment.create(context);
	        if(createdPayment!=null){
	            List<Links> links = createdPayment.getLinks();
	            for (Links link:links) {
	                if(link.getRel().equals("approval_url")){
	                    redirectUrl = link.getHref();
	                    break;
	                }
	            }
	            response.put("status", "success");
	            response.put("redirect_url", redirectUrl);
	            
	        }
	    } catch (PayPalRESTException e) {
	        System.out.println("Error happened during payment creation!");
	    }
	    return response;
	}
	
	
	public ResponseEntity<MessageResponse> completePayment(HttpServletRequest req){
		MessageResponse mr = new MessageResponse("Thanh toán thành công");
	    Payment payment = new Payment();
	    payment.setId(req.getParameter("paymentId"));

	    PaymentExecution paymentExecution = new PaymentExecution();
	    paymentExecution.setPayerId(req.getParameter("payerId"));
	    try {
	        APIContext context = new APIContext(clientId, clientSecret, "sandbox");
	        Payment createdPayment = payment.execute(context, paymentExecution);
	        if(createdPayment!=null){
	        	createPaymentTransaction(Integer.parseInt(req.getParameter("walletId")), req.getParameter("sum"));
	            return new ResponseEntity<MessageResponse>(mr, new HttpHeaders(), HttpStatus.OK);
	        }
	    } catch (PayPalRESTException e) {
	        System.err.println(e.getDetails());        
	    }
	    return new ResponseEntity<MessageResponse>(new MessageResponse("Thanh Toán thất bại"), new HttpHeaders(), HttpStatus.OK);
	}
	
	public void createPaymentTransaction(int walletId, String sum ) {
		Wallet wallet = walletService.getById(walletId);
		Timestamp nowMoment = Timestamp.from(Instant.now());
		
		Payments newPayment = new Payments();
		newPayment.setWallet(wallet);
		float totalPrice = Float.parseFloat(sum);
		newPayment.setTotalPrice(totalPrice);
		newPayment.setProvider("PAYPAL");
		newPayment.setCreatedAt(nowMoment);
		newPayment.setCancelUrl("http://localhost:4000/cancel");
		newPayment.setSuccessUrl("http://localhost:4000/redirect");	
		paymentService.add(newPayment);
		
		wallet.setBalance(wallet.getBalance() + totalPrice);
		walletService.add(wallet);
		
		Transactions newTransaction = new Transactions();
		newTransaction.setComfirms(true);
		newTransaction.setPayment(newPayment);
		newTransaction.setDescription("Top up wallet: " + wallet.getUser().getUsername());
		newTransaction.setTotal(totalPrice);
		newTransaction.setStatus(true);
		newTransaction.setCreatedAt(nowMoment);
		transactionsService.add(newTransaction);
		
	}

}
