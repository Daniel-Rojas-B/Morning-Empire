package com.morningempire.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.morningempire.models.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long>{
	
	List<Order> findAll();
	
	List<Order> findByUser_UserId(Long userId);
}
