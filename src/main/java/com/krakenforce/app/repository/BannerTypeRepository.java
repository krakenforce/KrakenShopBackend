package com.krakenforce.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.krakenforce.app.model.BannerType;

public interface BannerTypeRepository extends JpaRepository<BannerType, Integer>,
PagingAndSortingRepository<BannerType, Integer>{

}
