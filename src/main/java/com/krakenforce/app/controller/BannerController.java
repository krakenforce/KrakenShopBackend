package com.krakenforce.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.krakenforce.app.model.BannerType;
import com.krakenforce.app.model.HomePageBanner;
import com.krakenforce.app.model.SalePromote;
import com.krakenforce.app.service.BannerTypeService;
import com.krakenforce.app.service.FileStorageService;
import com.krakenforce.app.service.HomePageBannerService;
import com.krakenforce.app.service.SalePromoteService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/banner")
public class BannerController {
	
	@Autowired
	FileStorageService fileStorageService;

	@Autowired
	BannerTypeService bannerTypeService;

	@Autowired
	HomePageBannerService homePageBannerService;

	@Autowired
	SalePromoteService salePromoteService;

	// Banner

	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<HomePageBanner> addHomePageBanner(@RequestPart(value = "banner", required = true) HomePageBanner homePageBanner,
			@RequestPart(value="file", required=true) MultipartFile file) {
		try {
			BannerType type = bannerTypeService.getById(2);
			homePageBanner.setBannerType(type);
			homePageBanner.setImageUrl(getImagePath(file));
			homePageBannerService.add(homePageBanner);
			return new ResponseEntity<HomePageBanner>(homePageBanner, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<HomePageBanner>(null, new HttpHeaders(), HttpStatus.OK);
		}
	}
	
	/*use to get Image path when upload*/
	public String getImagePath(MultipartFile file) {
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/api/files/")
				.path(fileName)
				.toUriString();
		return fileDownloadUri;
	}

	@GetMapping("/{typeId}")
	public ResponseEntity<List<HomePageBanner>> getBannerByType(@PathVariable("typeId") int typeId) {
		try {
			List<HomePageBanner> homePageBanners = homePageBannerService.getBannerByTypeId(typeId);
			return new ResponseEntity<List<HomePageBanner>>(homePageBanners, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<HomePageBanner>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping()
	public ResponseEntity<Map<String, Object>> getAllBanner(@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy) {
		try {
			Map<String, Object> response = homePageBannerService.getAllBanner(pageNo, pageSize, sortBy);
			return new ResponseEntity<Map<String, Object>>(response, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Map<String, Object>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

	// SALE PROMOTE

	@PostMapping("/sale_promote")
	public ResponseEntity<SalePromote> addSalePromote(@RequestBody SalePromote salePromote) {
		try {
			salePromoteService.add(salePromote);
			return new ResponseEntity<SalePromote>(salePromote, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<SalePromote>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/sale_promote/{salePromoteId}")
	public ResponseEntity<SalePromote> getSalePromoteById(@PathVariable("salePromoteId") int salePromoteId) {
		try {
			SalePromote salePromote = salePromoteService.getById(salePromoteId);
			return new ResponseEntity<SalePromote>(salePromote, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<SalePromote>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/sale_promote")
	public ResponseEntity<Map<String, Object>> getAllSalePromote(
			@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy){
		try {
			Map<String, Object> response = salePromoteService.getAllSalePromote(pageNo, pageSize, sortBy);
			return new ResponseEntity<Map<String, Object>>(response, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Map<String, Object>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/sale_promote/search")
	public ResponseEntity<Map<String, Object>> getSalePromoteByKeyword(@RequestParam("keyword") String keyword,
			@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy) {
		try {
			Map<String, Object> response = salePromoteService.getSalePromoteByKeyword(keyword, pageNo, pageSize, sortBy);
			return new ResponseEntity<Map<String, Object>>(response, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Map<String, Object>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	

}
