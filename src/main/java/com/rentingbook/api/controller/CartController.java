package com.rentingbook.api.controller;

import com.rentingbook.api.model.account.Account;
import com.rentingbook.api.model.cart.Cart;
import com.rentingbook.api.service.AccountService;
import com.rentingbook.api.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account/cart")
public class CartController {
    private CartService cartService;
    private AccountService accountService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    @PutMapping
    public ResponseEntity<?> saveCart(@RequestBody Cart cart) {
        if (cart != null)
            return ResponseEntity.ok(cartService.save(cart));
        return ResponseEntity.badRequest().body("You have to send data of cart as request body");
    }

    @GetMapping
    public Cart getCart() {
        Cart cart = accountService.getCurrentAccount().getCart();
        if (cart == null) {
            Account account = accountService.getCurrentAccount();
            cart = cartService.save(new Cart());
            account.setCart(cart);
            accountService.save(account);
        }
        return cart;
    }
}
