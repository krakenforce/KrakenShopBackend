package com.krakenforce.app.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.krakenforce.app.repository.stats.CategogyStats;
import com.krakenforce.app.repository.stats.ProductStats;
import com.krakenforce.app.repository.stats.TagStats;
import com.krakenforce.app.repository.stats.UserStats;
import com.krakenforce.app.service.OrderService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {
	
	@Autowired
	OrderService orderService;
	
	
	/* SALE STATISTICS */
	
	//--total revenue
	@GetMapping("/total_revenue")
	public ResponseEntity<Float> getTotalRevenue(){
		try {
			float total = orderService.getTotalRevenue();
			return new ResponseEntity<Float>(total, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Float>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/total_revenue_time")
	public ResponseEntity<Float> getTotalRevenueByTime(@RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime){
		try {
			Timestamp start = Timestamp.valueOf(startTime);
			Timestamp end = Timestamp.valueOf(endTime);
			float total = orderService.getRevenueByTime(start, end);
			return new ResponseEntity<Float>(total, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Float>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	//-- revunue by time
	@GetMapping("/revenue_time")
	public ResponseEntity<Float> getRevenueByTime(@RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime){
		try {
			Timestamp start = Timestamp.valueOf(startTime);
			Timestamp end = Timestamp.valueOf(endTime);
			float total = orderService.getRevenueByTime(start, end);
			return new ResponseEntity<Float>(total, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Float>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	//-- show number of order each day with time (default is this month)
	
	/* PRODUCT STATISTICS */
	// number of product sell by time
	@GetMapping("/product")
	public ResponseEntity<List<ProductStats>> getProductStatAllTime(){
		try {
			List<ProductStats> list = orderService.getCountOfProduct();
			return new ResponseEntity<List<ProductStats>>(list, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ProductStats>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/product_time")
	public ResponseEntity<List<ProductStats>> getProductStatByTime(@RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime){
		try {
			Timestamp start = Timestamp.valueOf(startTime);
			Timestamp end = Timestamp.valueOf(endTime);
			List<ProductStats> list = orderService.getCountOfProductByTime(start, end);
			return new ResponseEntity<List<ProductStats>>(list, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ProductStats>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/product/user_id")
	public ResponseEntity<List<ProductStats>> getProductStatByUser(@RequestParam("userId") int userId){
		try {
			List<ProductStats> list = orderService.getCountOfProductByUser(userId);
			return new ResponseEntity<List<ProductStats>>(list, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ProductStats>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	// number of cagegory sale
	@GetMapping("/category")
	public ResponseEntity<List<CategogyStats>> getCategoryStat(){
		try {
			List<CategogyStats> list = orderService.getCountOfCategory();
			return new ResponseEntity<List<CategogyStats>>(list, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<CategogyStats>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/category_time")
	public ResponseEntity<List<CategogyStats>> getCategoryStatByTime(@RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime){
		try {
			Timestamp start = Timestamp.valueOf(startTime);
			Timestamp end = Timestamp.valueOf(endTime);
			List<CategogyStats> list = orderService.getCountOfCategoryByTime(start, end);
			return new ResponseEntity<List<CategogyStats>>(list, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<CategogyStats>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	// number sale by tag
	@GetMapping("/tag")
	public ResponseEntity<List<TagStats>> getTagStat(){
		try {
			List<TagStats> list = orderService.getCountOfTag();
			return new ResponseEntity<List<TagStats>>(list, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<TagStats>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/tag_time")
	public ResponseEntity<List<TagStats>> getTagStatByTime(@RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime){
		try {
			Timestamp start = Timestamp.valueOf(startTime);
			Timestamp end = Timestamp.valueOf(endTime);
			List<TagStats> list = orderService.getCountOfTagByTime(start, end);
			return new ResponseEntity<List<TagStats>>(list, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<TagStats>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	/* USER STATISTICS */
	//user spend by time 
	@GetMapping("/user")
	public ResponseEntity<List<UserStats>> getUserStat(){
		try {
			List<UserStats> list = orderService.getTotalByUser();
			return new ResponseEntity<List<UserStats>>(list, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<UserStats>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	// show on table
	
}
