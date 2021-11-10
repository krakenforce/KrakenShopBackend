package com.krakenforce.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.OrderDetail;


@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>,
PagingAndSortingRepository<OrderDetail, Integer>{

	@Query(value = "SELECT * FROM order_detail WHERE order_id = ?1", nativeQuery = true)
	Page<OrderDetail> findByOrderId(int orderId, Pageable pageable);
	
	@Query(value = "select * from wallet \r\n"
			+ "inner join orders on wallet.id = orders.wallet_id\r\n"
			+ "inner join order_detail on orders.id = order_detail.order_id where wallet.id = ?1", nativeQuery = true)
	List<?> findOrderDetailByWalletId(int walletId);
}
