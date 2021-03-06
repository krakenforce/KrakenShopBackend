package com.krakenforce.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.ProductGameCode;


@Repository
public interface ProductGameCodeRepository extends JpaRepository<ProductGameCode, Integer>,
PagingAndSortingRepository<ProductGameCode, Integer>{
	
	@Query(value = "SELECT * FROM product_game_code WHERE product_id = ?1", nativeQuery = true)
	Page<ProductGameCode> findGameCodeByProduct(int productId, Pageable pageable);
	
	@Query(value = "SELECT * FROM product_game_code WHERE product_id = ?1 AND status = ?2", nativeQuery = true)
	List<ProductGameCode> findGameCodeByProductIdAndStatus(int productId, boolean status);
	
	@Query(value = "SELECT * FROM product_game_code WHERE product_id = ?1 AND status = ?2", nativeQuery = true)
	Page<ProductGameCode> findGameCodeByProductAndStatus(int productId, boolean status, Pageable pageable);

	@Query("SELECT COUNT(pgc) FROM ProductGameCode pgc WHERE pgc.product.productId = ?1")
	long countGameCodeByProduct(int productId);
	
	@Query("SELECT COUNT(pgc) FROM ProductGameCode pgc WHERE pgc.product.productId = ?1 AND pgc.status = ?2")
	long countGameCodeByProductAndStatus(int productId , boolean status);
	
	@Query(value = "select pc.* from wallet as w\r\n"
			+ "inner join orders as o on w.id = o.wallet_id\r\n"
			+ "inner join order_detail as od on o.id = od.order_id\r\n"
			+ "inner join product_game_code as pc on pc.id = od.product_game_code_id where w.id = ?1",nativeQuery = true )
	Page<ProductGameCode> findGameCodeByWalletId(int walletId, Pageable pageable);
}
