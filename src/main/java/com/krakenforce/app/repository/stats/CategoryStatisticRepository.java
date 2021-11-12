package com.krakenforce.app.repository.stats;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.Orders;

@Repository
public interface CategoryStatisticRepository extends org.springframework.data.repository.Repository<Orders, Integer> {

	@Query(value = "select category.name as categoryName,  count(*) as amount from orders\r\n"
			+ "join order_detail on order_detail.order_id = orders.id\r\n"
			+ "join product_game_code on product_game_code.id = order_detail.product_game_code_id\r\n"
			+ "join product on product.product_id = product_game_code.product_id\r\n"
			+ "join category_product on category_product.product_id = product.product_id\r\n"
			+ "join category on category_product.category_id = category.category_id\r\n"
			+ "group by category.name", nativeQuery = true)
	List<CategogyStats> getCategoryCount();

	@Query(value = "select category.name as categoryName,  count(*) as amount from orders\r\n"
			+ "join order_detail on order_detail.order_id = orders.id\r\n"
			+ "join product_game_code on product_game_code.id = order_detail.product_game_code_id\r\n"
			+ "join product on product.product_id = product_game_code.product_id\r\n"
			+ "join category_product on category_product.product_id = product.product_id\r\n"
			+ "join category on category_product.category_id = category.category_id\r\n"
			+ "where order_detail.created_at between ?1 and ?2 group by category.name", nativeQuery = true)
	List<CategogyStats> getCategoryCountByTime(Timestamp startTime, Timestamp endTime);
}
