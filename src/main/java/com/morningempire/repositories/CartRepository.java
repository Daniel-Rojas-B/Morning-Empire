package com.morningempire.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.morningempire.models.Cart;
import com.morningempire.models.Product;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long>{
	
	List<Cart> findAll();
	
	Optional<Cart> findByUser_UserId(Long userId);
	
	// Method to find an active cart by user ID
    Optional<Cart> findByUser_UserIdAndActive(Long userId, boolean active);
}
