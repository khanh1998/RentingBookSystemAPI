package com.rentingbook.api.service.implement;

import com.rentingbook.api.model.cart.Cart;
import com.rentingbook.api.repository.CartRepository;
import com.rentingbook.api.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    private CartRepository cartRepository;

    @Autowired
    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.saveAndFlush(cart);
    }
}
