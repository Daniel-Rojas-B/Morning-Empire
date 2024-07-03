package com.morningempire.services;
import com.morningempire.models.OrderItem;
import com.morningempire.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {
	
	private final OrderItemRepository orderItemRepository;
	
	@Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional
    public List<OrderItem> findAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @Transactional
    public Optional<OrderItem> findOrderItemById(Long id) {
        return orderItemRepository.findById(id);
    }

    @Transactional
    public OrderItem saveOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Transactional
    public void deleteOrderItemById(Long id) {
        orderItemRepository.deleteById(id);
    }

    // Other business logic related to OrderItemService can be added here
}
