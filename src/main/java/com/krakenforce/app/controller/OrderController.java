package com.krakenforce.app.controller;

import java.sql.Timestamp;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.krakenforce.app.dtos.GameCodeDetail;
import com.krakenforce.app.model.OrderDetail;
import com.krakenforce.app.model.Orders;
import com.krakenforce.app.model.ProductGameCode;
import com.krakenforce.app.model.Wallet;
import com.krakenforce.app.security.common.MessageResponse;
import com.krakenforce.app.service.OrderDetailService;
import com.krakenforce.app.service.OrderService;
import com.krakenforce.app.service.ProductGameCodeService;
import com.krakenforce.app.service.WalletService;

@CrossOrigin(origins="*", maxAge = 3600)
@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderDetailService orderDetailService;
	
	@Autowired
	WalletService walletService;
	
	@Autowired
	ProductGameCodeService productGameCodeService;
	
	// ORDER
	
	@PostMapping()
	public ResponseEntity<MessageResponse> createOrder(@RequestParam("walletId") int walletId
			,@RequestPart("order") Orders order
			,@RequestPart("gameCodeDetail") List<GameCodeDetail> gameCodeDetails){
		try {
			Date date = new Date();
			Timestamp timestamp2 = new Timestamp(date.getTime());
			Wallet wallet = walletService.getById(walletId);
			
			order.setWallet(wallet);
			order.setOrderDateTime(timestamp2);
			order.setStatus(true);
			Orders newOrder = orderService.add(order);
			
			for(GameCodeDetail itemGameCode : gameCodeDetails) {
				int productId = itemGameCode.getProductId();
				int quantityDetail = itemGameCode.getQuantity();
				List<ProductGameCode> list = productGameCodeService.getProductGameCodeByIdAndStatus(productId, true);
				List<ProductGameCode> childList = list.subList(0, quantityDetail);
				
				for(ProductGameCode item : childList) {
					OrderDetail newOrderDetail = new OrderDetail();
					newOrderDetail.setCreatedAt(timestamp2);
					newOrderDetail.setProductGameCode(item);
					newOrderDetail.setOrder(newOrder);
					newOrderDetail.setStatus(true);
					orderDetailService.add(newOrderDetail);
					item.setStatus(false);
					productGameCodeService.add(item);
					
				}
			}
			
			return new ResponseEntity<MessageResponse>(new MessageResponse("Add order success"), new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<MessageResponse>(new MessageResponse("Add order fail"), new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
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
	public ResponseEntity<Map<String, Object>> getAll(@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy){
		try {
			Map<String, Object> response = orderService.getAll(pageNo, pageSize, sortBy);
			return new ResponseEntity<Map<String, Object>>(response, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search")
	public ResponseEntity<Map<String, Object>> getByTime(@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy,
			@RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime){
		try {
			Timestamp start = Timestamp.valueOf(startTime);
			Timestamp end = Timestamp.valueOf(endTime);
			Map<String, Object> response = orderService.getByTime(start, end, pageNo, pageSize, sortBy);
			return new ResponseEntity<Map<String, Object>>(response, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Map<String, Object>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/search_user")
	public ResponseEntity<Map<String, Object>> getByUser(@RequestParam("walletId") int walletId,
			@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy){
		try {
			Map<String, Object> response = orderService.getAll(pageNo, pageSize, sortBy);
			return new ResponseEntity<Map<String, Object>>(response, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	//ORDER DETAIL
	
	@PostMapping("/order_detail")
	public ResponseEntity<OrderDetail> addOrderDetail(@RequestBody OrderDetail orderDetail){
		try {
			orderDetailService.add(orderDetail);
			return new ResponseEntity<OrderDetail>(orderDetail, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<OrderDetail>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
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
			return new ResponseEntity<List<OrderDetail>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/order_detail/{orderId}")
	public ResponseEntity<Map<String, Object>> getOrderDetailByOrderId(@PathVariable("orderId") int orderId,
			@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy){
		try {
			Map<String, Object> orderDetails = orderDetailService.getByOrderId(orderId, pageNo, pageSize, sortBy);
			return new ResponseEntity<Map<String, Object>>(orderDetails, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/order_detail/get_by_wallet/{walletId}")
	public ResponseEntity<List<?>> getOrderDetailByWallet(@PathVariable("walletId") int wallet){
		try {
			List<?> list = orderDetailService.getOrderDetailByWalletId(wallet);
			return new ResponseEntity<List<?>>(list, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<?>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

}
