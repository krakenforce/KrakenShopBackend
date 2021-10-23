package com.krakenforce.app.controller;

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

import com.krakenforce.app.model.Tag;
import com.krakenforce.app.security.common.MessageResponse;
import com.krakenforce.app.service.TagService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/tag")
public class TagController {
	
	@Autowired
	TagService TagService;
	
	@PostMapping()
	public ResponseEntity<Tag> addTag(@RequestBody Tag Tag){
		try {
			Tag result = TagService.add(Tag);
			return new ResponseEntity<Tag>(result, new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<Tag>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping()
	public ResponseEntity<Tag> updateTag(@RequestBody Tag Tag){
		try {
			Tag result = TagService.update(Tag);
			return new ResponseEntity<Tag>(result, new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<Tag>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{TagId}")
	public ResponseEntity<MessageResponse> deleteTag(@PathVariable("TagId") int TagId){
		try {
			TagService.delete(TagId);
			return new ResponseEntity<MessageResponse>(new MessageResponse("delete success"), new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("delete fail"), new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping()
	public ResponseEntity<List<Tag>> getAllTag(@RequestParam(defaultValue ="0") int pageNo,
			@RequestParam(defaultValue ="10") int pageSize,
			@RequestParam(defaultValue ="tagId") String sortBy){
		try {
			List<Tag> list = TagService.getAllTag(pageNo, pageSize, sortBy);
			return new ResponseEntity<List<Tag>>(list, new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<List<Tag>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/{TagId}")
	public ResponseEntity<Tag> getTagById(@PathVariable("TagId") int TagId){
		try {
			Tag Tag = TagService.getById(TagId);
			return new ResponseEntity<Tag>(Tag, new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<Tag>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Tag>> getAllTagByKeyword(@RequestParam("keyword") String keyword,
			@RequestParam(defaultValue ="0") int pageNo,
			@RequestParam(defaultValue ="10") int pageSize,
			@RequestParam(defaultValue ="tag_id") String sortBy){
		try {
			List<Tag> list = TagService.getAllTagByName(keyword, pageNo, pageSize, sortBy);
			return new ResponseEntity<List<Tag>>(list, new HttpHeaders(), HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<List<Tag>>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}
}
