package com.morningempire.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.morningempire.models.CartItem;
import com.morningempire.models.OrderItem;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Long>{
	
	List<OrderItem> findAll();
	
	List<OrderItem> findByOrder_OrderId(Long orderId);

    List<OrderItem> findByProduct_ProductId(Long productId);
}
