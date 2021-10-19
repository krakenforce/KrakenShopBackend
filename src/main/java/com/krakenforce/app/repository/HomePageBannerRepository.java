package com.krakenforce.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.HomePageBanner;


@Repository
public interface HomePageBannerRepository extends JpaRepository<HomePageBanner, Integer>,
PagingAndSortingRepository<HomePageBanner, Integer>{

}