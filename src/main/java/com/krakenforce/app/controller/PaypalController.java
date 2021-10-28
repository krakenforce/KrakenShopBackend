package com.krakenforce.app.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.krakenforce.app.security.common.MessageResponse;
import com.krakenforce.app.service.PaypalClient;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/paypal")
public class PaypalController {
	
	@Autowired
	PaypalClient paypalClient;
	
	@PostMapping(value = "/make/payment")
    public Map<String, Object> makePayment(@RequestParam("sum") String sum){
		
        return paypalClient.createPayment(sum);
    }
	
	@PostMapping(value = "/complete/payment")
	public ResponseEntity<MessageResponse> completePayment(HttpServletRequest request){
	    return paypalClient.completePayment(request);
	}
}
