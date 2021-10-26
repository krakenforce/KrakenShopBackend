package com.krakenforce.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.HomePageBanner;


@Repository
public interface HomePageBannerRepository extends JpaRepository<HomePageBanner, Integer>,
PagingAndSortingRepository<HomePageBanner, Integer>{

	@Query(value = "SELECT * FROM home_page_banner WHERE banner_type_id = ?1", nativeQuery = true)
	List<HomePageBanner> findBannerByType(int typeId);
}
