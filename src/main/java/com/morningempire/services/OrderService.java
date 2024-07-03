package com.morningempire.services;
import com.morningempire.models.Order;
import com.morningempire.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
	private final OrderRepository orderRepository;
	
	@Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Transactional
    public Order createOrder(Order order) {
        // Perform any business logic or validation before saving
        // E.g., calculate total, validate items, etc.
        return orderRepository.save(order);
    }

    @Transactional
    public Order updateOrder(Order order) {
        // Perform any business logic or validation before updating
        // E.g., update status, recalculate total, etc.
        return orderRepository.save(order);
    }

    @Transactional
    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }

    // Other business logic related to OrderService can be added here
}
