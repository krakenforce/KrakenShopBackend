package com.krakenforce.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krakenforce.app.repository.ProductWarrantyRepository;

@Service
@Transactional
public class ProductWarrantyService {

	@Autowired
	private ProductWarrantyRepository productWarrantyRepository;
}
