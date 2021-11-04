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

import com.krakenforce.app.model.OrderDetail;
import com.krakenforce.app.model.Orders;
import com.krakenforce.app.security.common.MessageResponse;
import com.krakenforce.app.service.OrderDetailService;
import com.krakenforce.app.service.OrderService;

@CrossOrigin(origins="*", maxAge = 3600)
@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderDetailService orderDetailService;
	
	// ORDER
	
	@PostMapping()
	public ResponseEntity<Orders> addOrder(@RequestBody Orders orders){
		try {
			orderService.add(orders);
			return new ResponseEntity<Orders>(orders, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Orders>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{orderId}")
	public ResponseEntity<MessageResponse> deleteOrder(@PathVariable("orderId") int orderId){
		try {
			orderService.delete(orderId);
			return new ResponseEntity<MessageResponse>(new MessageResponse("Delete success"), new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Delete fail"), new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping("/{orderId}")
	public ResponseEntity<Orders> getOrderById(@PathVariable("orderId") int orderId){
		try {
			Orders order  = orderService.getById(orderId);
			return new ResponseEntity<Orders>(order, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Orders>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping()
	public ResponseEntity<List<Orders>> getAll(@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "0") int pageSize,
			@RequestParam(defaultValue = "orderId") String sortBy){
		try {
			List<Orders> orders = orderService.getAll(pageNo, pageSize, sortBy);
			return new ResponseEntity<List<Orders>>(orders, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Orders>>(null, new HttpHeaders(), HttpStatus.OK);
		}
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Orders>> getByTime(@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "0") int pageSize,
			@RequestParam(defaultValue = "orderId") String sortBy,
			@RequestParam("startTime") Instant startTime,
			@RequestParam("endTime") Instant endTime){
		try {
			List<Orders> orders = orderService.getByTime(startTime, endTime, pageNo, pageSize, sortBy);
			return new ResponseEntity<List<Orders>>(orders, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Orders>>(null, new HttpHeaders(), HttpStatus.OK);
		}
	}
	
	//ORDER DETAIL
	
	@PostMapping("/order_detail")
	public ResponseEntity<OrderDetail> addOrderDetail(@RequestBody OrderDetail orderDetail){
		try {
			orderDetailService.add(orderDetail);
			return new ResponseEntity<OrderDetail>(orderDetail, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<OrderDetail>(null, new HttpHeaders(), HttpStatus.OK);
		}
		
	}
	
	@DeleteMapping("/order_detail/{orderDetailId}")
	public ResponseEntity<MessageResponse> deleteOrderDetail(@PathVariable("orderDetailId") int orderDetailId){
		try {
			orderDetailService.delete(orderDetailId);
			return new ResponseEntity<MessageResponse>(new MessageResponse("Delete Success"), new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Delete Fail"), new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/order_detail")
	public ResponseEntity<List<OrderDetail>> getAllOrderDetail(){
		try {
			List<OrderDetail> orderDetails = orderDetailService.getAll();
			return new ResponseEntity<List<OrderDetail>>(orderDetails, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<OrderDetail>>(null, new HttpHeaders(), HttpStatus.OK);
		}
	}
	
	@GetMapping("/order_detail/{orderId}")
	public ResponseEntity<List<OrderDetail>> getOrderDetailByOrderId(@PathVariable("orderId") int orderId){
		try {
			List<OrderDetail> orderDetails = orderDetailService.getByOrderId(orderId);
			return new ResponseEntity<List<OrderDetail>>(orderDetails, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<OrderDetail>>(null, new HttpHeaders(), HttpStatus.OK);
		}
	}
	

}
