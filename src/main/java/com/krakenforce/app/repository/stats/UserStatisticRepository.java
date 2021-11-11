package com.krakenforce.app.repository.stats;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.krakenforce.app.model.Orders;

@Repository
public interface UserStatisticRepository extends org.springframework.data.repository.Repository<Orders, Integer> {
	
	@Query(value = "select users.user_id as userId, users.username as username, round(sum(total),2) as total from users \r\n"
			+ "join wallet on users.user_id = wallet.user_id\r\n"
			+ "join orders on orders.wallet_id = wallet.id\r\n"
			+ "group by users.username", nativeQuery = true)
	List<UserStats> getSumTotalUser();
	
	@Query(value = "select users.user_id as userId, users.username as username, round(sum(total),2) as total from users \r\n"
			+ "join wallet on users.user_id = wallet.user_id\r\n"
			+ "join orders on orders.wallet_id = wallet.id\r\n"
			+ "WHERE orders.order_datetime BETWEEN ?1 AND ?2 group by users.username", nativeQuery = true)
	List<UserStats> getSumTotalUserByTime(Timestamp startTime, Timestamp endTime);
}
