package com.morningempire.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.morningempire.models.CartItem;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem, Long> {
	List<CartItem> findAll();
	
	// Method to find a cart item by cart ID and product ID
    Optional<CartItem> findByCart_CartIdAndProduct_ProductId(Long cartId, Long productId);
	
	Optional<CartItem> findById(Long cartItemId);
	//	This method retrieves all CartItem entities associated with a specific cartId.
	List<CartItem> findByCart_CartId(Long cartId);
	
	//	Retrieves all CartItem entities that contain a specific productId.
	List<CartItem> findByProduct_ProductId(Long productId);
	
	// Counts the number of CartItem entities associated with a specific cartId.
	long countByCart_CartId(Long cartId);
	
	// Deletes all CartItem entities associated with a specific cartId.
	void deleteByCart_CartId(Long cartId);
	
	// Method to delete a cart item by cart ID and product ID
    void deleteByCart_CartIdAndProduct_ProductId(Long cartId, Long productId);
}
