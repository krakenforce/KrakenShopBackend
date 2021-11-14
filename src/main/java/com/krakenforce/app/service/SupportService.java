package com.krakenforce.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krakenforce.app.model.Support;
import com.krakenforce.app.repository.SupportRepository;

@Service
@Transactional
public class SupportService {

	@Autowired
	private SupportRepository supportRepository;
	
	public Support add(Support support) {
		return supportRepository.save(support);	
	}
	
	public Support update(Support support) {
		Support selectedSupport = supportRepository.getById(support.getId());
		selectedSupport = support;
		return supportRepository.save(selectedSupport);
	}
	
	public void delete(int supportId) {
		supportRepository.deleteById(supportId);
	}
	
	public List<Support> getAll(){
		return supportRepository.findAll();
	}
	
	public Support getById(int supportId) {
		return supportRepository.findById(supportId).orElse(null);
	}
}
