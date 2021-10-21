package com.krakenforce.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krakenforce.app.repository.TagRepository;

@Service
@Transactional
public class TagService {

	@Autowired
	private TagRepository tagRepository;
}
