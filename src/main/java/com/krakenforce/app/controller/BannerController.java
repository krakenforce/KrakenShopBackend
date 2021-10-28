package com.krakenforce.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.krakenforce.app.model.BannerType;
import com.krakenforce.app.model.HomePageBanner;
import com.krakenforce.app.model.SalePromote;
import com.krakenforce.app.service.BannerTypeService;
import com.krakenforce.app.service.HomePageBannerService;
import com.krakenforce.app.service.SalePromoteService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/banner")
public class BannerController {

	@Autowired
	BannerTypeService bannerTypeService;
	
	@Autowired
	HomePageBannerService homePageBannerService;
	
	@Autowired
	SalePromoteService salePromoteService; 
	
	//Banner
	
	@PostMapping()
	public ResponseEntity<HomePageBanner> addHomePageBanner(@RequestBody HomePageBanner homePageBanner,
			@RequestParam("typeId") int bannerTypeId){
		try {
			BannerType type = bannerTypeService.getById(bannerTypeId);
			homePageBanner.setBannerType(type);
			homePageBannerService.add(homePageBanner);
			return new ResponseEntity<HomePageBanner>(homePageBanner, new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<HomePageBanner>(null, new HttpHeaders(), HttpStatus.OK);
		}
	}
	
	@GetMapping("/{typeId}")
	public ResponseEntity<List<HomePageBanner>> getBannerByType(@PathVariable("typeId") int typeId){
		try {
			List<HomePageBanner> homePageBanners = homePageBannerService.getBannerByTypeId(typeId);
			return new ResponseEntity<List<HomePageBanner>>(homePageBanners, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<HomePageBanner>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping()
	public ResponseEntity<List<HomePageBanner>> getAllBanner(){
		try {
			List<HomePageBanner> homePageBanners = homePageBannerService.getAll();
			return new ResponseEntity<List<HomePageBanner>>(homePageBanners, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<HomePageBanner>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	//SALE PROMOTE
	
	@PostMapping("/sale_promote")
	public ResponseEntity<SalePromote> addSalePromote(@RequestBody SalePromote salePromote){
		try {
			salePromoteService.add(salePromote);
			return new ResponseEntity<SalePromote>(salePromote, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<SalePromote>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/sale_promote/{salePromoteId}")
	public ResponseEntity<SalePromote> getSalePromoteById(@PathVariable("salePromoteId") int salePromoteId){
		try {
			SalePromote salePromote = salePromoteService.getById(salePromoteId);
			return new ResponseEntity<SalePromote>(salePromote, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<SalePromote>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/sale_promote")
	public ResponseEntity<List<SalePromote>> getAllSalePromote(){
		try {
			List<SalePromote> salePromotes = salePromoteService.getAll();
			return new ResponseEntity<List<SalePromote>>(salePromotes, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<SalePromote>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/sale_promote/search")
	public ResponseEntity<List<SalePromote>> getSalePromoteByKeyword(@RequestParam("keyword") String keyword){
		try {
			List<SalePromote> salePromotes = salePromoteService.getByKeyword(keyword);
			return new ResponseEntity<List<SalePromote>>(salePromotes, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<SalePromote>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	
	
}
