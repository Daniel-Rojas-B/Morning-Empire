package com.morningempire.services;

import com.morningempire.models.Cart;
import com.morningempire.models.CartItem;
import com.morningempire.repositories.CartRepository;
import com.morningempire.repositories.CartItemRepository;
import com.morningempire.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    @Autowired
    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<Cart> findAllCarts() {
        return cartRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Cart> findCartById(Long id) {
        return cartRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Cart> findActiveCartByUserId(Long userId) {
        return cartRepository.findByUser_UserIdAndActive(userId, true);
    }

    @Transactional
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Transactional
    public void deleteCartById(Long id) {
        cartRepository.deleteById(id);
    }

    @Transactional
    public Cart addProductToCart(Long cartId, Long productId, int quantity) {
        Optional<Cart> cartOpt = cartRepository.findById(cartId);
        if (cartOpt.isPresent()) {
            Cart cart = cartOpt.get();
            Optional<CartItem> cartItemOpt = cartItemRepository.findByCart_CartIdAndProduct_ProductId(cartId, productId);
            CartItem cartItem = cartItemOpt.orElse(new CartItem(cart, productId, quantity));
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItemRepository.save(cartItem);
            return cart;
        }
        throw new RuntimeException("Cart not found");
    }

    @Transactional
    public Cart removeProductFromCart(Long cartId, Long productId) {
        Optional<Cart> cartOpt = cartRepository.findById(cartId);
        if (cartOpt.isPresent()) {
            Cart cart = cartOpt.get();
            cartItemRepository.deleteByCart_CartIdAndProduct_ProductId(cartId, productId);
            return cart;
        }
        throw new RuntimeException("Cart not found");
    }

    @Transactional
    public void clearCart(Long cartId) {
        cartItemRepository.deleteByCart_CartId(cartId);
    }
}
