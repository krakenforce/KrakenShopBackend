package com.krakenforce.app.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.krakenforce.app.dtos.SupportCategoryDtos;
import com.krakenforce.app.dtos.SupportDtos;
import com.krakenforce.app.model.Support;
import com.krakenforce.app.model.SupportCategory;
import com.krakenforce.app.security.common.MessageResponse;
import com.krakenforce.app.service.SupportCategoryService;
import com.krakenforce.app.service.SupportService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/support")
public class SupportController {

	@Autowired
	SupportCategoryService supportCategoryService;

	@Autowired
	SupportService supportService;

	// ======================================================================================
	// SUPPORT CATEGORY MODULE
	/**
	 * @category SUPPORT CATEGORY MODULE
	 */

	@PostMapping("/category")
	public ResponseEntity<SupportCategory> addSupportCategory(@RequestBody SupportCategory supportCategory) {
		if (supportCategory != null) {
			supportCategoryService.add(supportCategory);
			return new ResponseEntity<SupportCategory>(supportCategory, HttpStatus.OK);
		} else {
			return new ResponseEntity<SupportCategory>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/category")
	public ResponseEntity<SupportCategory> updateSupportCategory(@RequestBody SupportCategory supportCategory) {
		if (supportCategory != null) {
			supportCategoryService.update(supportCategory);
			return new ResponseEntity<SupportCategory>(supportCategory, HttpStatus.OK);
		} else {
			return new ResponseEntity<SupportCategory>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/category/{categoryId}")
	public ResponseEntity<MessageResponse> deleteSupportCategory(@PathVariable("categoryId") int categoryId) {
		try {
			supportCategoryService.delete(categoryId);
			return new ResponseEntity<MessageResponse>(new MessageResponse("Delete successfully"), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Delete fail"), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/category")
	public ResponseEntity<List<SupportCategoryDtos>> getAllSupportCategory() {

		try {
			List<SupportCategory> list = supportCategoryService.getAllCategory();
			List<SupportCategoryDtos> dtosList = new ArrayList<SupportCategoryDtos>();
			for (SupportCategory item : list) {
				SupportCategoryDtos dto = new SupportCategoryDtos(item.getId(), item.getContent(), item.isStatus());
				dtosList.add(dto);
			}
			return new ResponseEntity<List<SupportCategoryDtos>>(dtosList, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<List<SupportCategoryDtos>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/category/{categoryId}")
	public ResponseEntity<SupportCategoryDtos> getSupportCategoryById(@PathVariable("categoryId") int categoryId) {
		try {
			SupportCategory item = supportCategoryService.getCategoryById(categoryId);
			if (item != null) {
				SupportCategoryDtos dtos = new SupportCategoryDtos(item.getId(), item.getContent(), item.isStatus());
				return new ResponseEntity<SupportCategoryDtos>(dtos, new HttpHeaders(), HttpStatus.OK);
			} else {
				return new ResponseEntity<SupportCategoryDtos>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
			}

		} catch (Exception ex) {
			return new ResponseEntity<SupportCategoryDtos>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}

	}

	// ===============================================================================================
	// SUPPORT MODULE
	/**
	 * @category SUPPORT MODULE
	 */

	@PostMapping("/support_info")
	public ResponseEntity<Support> addSupport(@RequestParam("categoryId") int categoryId,
			@RequestBody Support support) {
		SupportCategory category = supportCategoryService.getCategoryById(categoryId);
		if (support != null && category != null) {
			support.setSupportCategory(category);
			supportService.add(support);
			return new ResponseEntity<Support>(support, HttpStatus.OK);
		} else {
			return new ResponseEntity<Support>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/support_info")
	public ResponseEntity<SupportDtos> updateSupport(@RequestParam("categoryId") int categoryId,
			@RequestBody Support support) {
		SupportCategory category = supportCategoryService.getCategoryById(categoryId);
		if (support != null && category != null) {
			support.setSupportCategory(category);
			supportService.update(support);
			SupportDtos dto = new SupportDtos(support.getId(), support.getContent(), support.getContentLink(), support.getSupportCategory().getId(), support.isStatus());
			return new ResponseEntity<SupportDtos>(dto, HttpStatus.OK);
		} else {
			return new ResponseEntity<SupportDtos>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	

	@DeleteMapping("/support_info/{supportId}")
	public ResponseEntity<MessageResponse> deleteSupport(@PathVariable("supportId") int supportId) {
		try {
			supportService.delete(supportId);
			return new ResponseEntity<MessageResponse>(new MessageResponse("Delete successfully"), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Delete fail"), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/support_info")
	public ResponseEntity<List<SupportDtos>> getAllSupport() {

		try {
			List<Support> list = supportService.getAll();
			List<SupportDtos> dtosList = new ArrayList<SupportDtos>();
			for (Support item : list) {
				SupportDtos dto = new SupportDtos(item.getId(), item.getContent(), item.getContentLink(),
						item.getSupportCategory().getId(), item.isStatus());
				dtosList.add(dto);
			}
			return new ResponseEntity<List<SupportDtos>>(dtosList, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<List<SupportDtos>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/support_info/{supportId}")
	public ResponseEntity<SupportDtos> getSupportById(@PathVariable("supportId") int supportId) {
		try {
			Support item = supportService.getById(supportId);
			if(item != null) {
				SupportDtos dtos = new SupportDtos(item.getId(), item.getContent(), item.getContentLink(),
						item.getSupportCategory().getId(), item.isStatus());
				return new ResponseEntity<SupportDtos>(dtos, new HttpHeaders(), HttpStatus.OK);
			}else {
				return new ResponseEntity<SupportDtos>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
			}			
		} catch (Exception ex) {
			return new ResponseEntity<SupportDtos>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}

	}
}
