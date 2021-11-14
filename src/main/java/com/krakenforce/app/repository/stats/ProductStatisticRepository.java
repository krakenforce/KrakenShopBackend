package com.krakenforce.app.repository.stats;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.krakenforce.app.model.Orders;

@org.springframework.stereotype.Repository
public interface ProductStatisticRepository extends Repository<Orders, Integer>{
	
	@Query(value = "select p.name as productName, count(*) as amount from orders as o\r\n"
			+ "join order_detail as od on od.order_id = o.id\r\n"
			+ "join product_game_code as pc on pc.id = od.product_game_code_id\r\n"
			+ "join product as p on p.product_id = pc.product_id\r\n"
			+ "group by p.name", nativeQuery = true)
	List<ProductStats> getProductCount();
	
	@Query(value = "select p.name as productName, count(*) as amount from orders as o\r\n"
			+ "join order_detail as od on od.order_id = o.id\r\n"
			+ "join product_game_code as pc on pc.id = od.product_game_code_id\r\n"
			+ "join product as p on p.product_id = pc.product_id\r\n"
			+ "where o.order_datetime between ?1 and ?2 group by p.name", nativeQuery = true)
	List<ProductStats> getProductCountByTime(Timestamp startTime, Timestamp endTime);
	
	
	
	@Query(value = "select product.name as productName, count(*) as amount from users \r\n"
			+ "join wallet on users.user_id = wallet.user_id\r\n"
			+ "join orders on orders.wallet_id = wallet.id\r\n"
			+ "join order_detail on order_detail.order_id = orders.id\r\n"
			+ "join product_game_code on product_game_code.id = order_detail.product_game_code_id\r\n"
			+ "join product on product.product_id = product_game_code.product_id\r\n"
			+ "where users.user_id = ?1\r\n"
			+ "group by product.name", nativeQuery = true)
	List<ProductStats> getProductCountByUser(int userId);
	
}
