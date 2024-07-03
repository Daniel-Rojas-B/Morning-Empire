package com.morningempire.services;
import com.morningempire.models.CartItem;
import com.morningempire.repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Transactional(readOnly = true)
    public List<CartItem> findAllCartItems() {
        return cartItemRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<CartItem> findCartItemById(Long id) {
        return cartItemRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<CartItem> findCartItemsByCartId(Long cartId) {
        return cartItemRepository.findByCart_CartId(cartId);
    }

    @Transactional(readOnly = true)
    public List<CartItem> findCartItemsByProductId(Long productId) {
        return cartItemRepository.findByProduct_ProductId(productId);
    }

    @Transactional(readOnly = true)
    public CartItem findCartItemByCartIdAndProductId(Long cartId, Long productId) {
    	Optional<CartItem> cartItemOpt = cartItemRepository.findByCart_CartIdAndProduct_ProductId(cartId, productId);
        CartItem cartItem = cartItemOpt.orElseGet(() -> {
            CartItem newCartItem = new CartItem();
            // Set necessary fields
            newCartItem.setCartItemId(cartId);
            newCartItem.setCartItemId(productId);
            //???????? check last two lines
            newCartItem.setQuantity(0); // Or any default quantity
            return newCartItem;
        });
        return cartItem;
    }

    @Transactional
    public CartItem saveCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Transactional
    public void deleteCartItemById(Long id) {
        cartItemRepository.deleteById(id);
    }

    @Transactional
    public void deleteCartItemsByCartId(Long cartId) {
        cartItemRepository.deleteByCart_CartId(cartId);
    }
    
}
