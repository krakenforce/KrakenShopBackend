package com.krakenforce.app.repository.stats;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.Orders;

@Repository
public interface TagStatisticRepository extends org.springframework.data.repository.Repository<Orders, Integer> {
	
	@Query(value = "select tag.name as tagName,  count(*) as amount from orders\r\n"
			+ "join order_detail on order_detail.order_id = orders.id\r\n"
			+ "join product_game_code on product_game_code.id = order_detail.product_game_code_id\r\n"
			+ "join product on product.product_id = product_game_code.product_id\r\n"
			+ "join tag_product on tag_product.product_id = product.product_id\r\n"
			+ "join tag on tag_product.tag_id = tag.tag_id\r\n"
			+ "group by tag.name", nativeQuery = true)
	List<TagStats> getTagCount();
}
