package com.rentingbook.api.controller;

import com.rentingbook.api.model.account.Account;
import com.rentingbook.api.model.book.RentingBook;
import com.rentingbook.api.model.cart.Cart;
import com.rentingbook.api.service.AccountService;
import com.rentingbook.api.service.CartService;
import com.rentingbook.api.service.RentingBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    private AccountService accountService;
    private RentingBookService bookService;
    private CartService cartService;

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Autowired
    public void setBookService(RentingBookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<Account> getAccount() {
        return ResponseEntity.ok(accountService.getCurrentAccount());
    }
    @PostMapping("")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Cart cart = new Cart();
        cart.setBooks(new ArrayList<>());
        account.setCart(cart);

        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.save(account));
    }

    @PutMapping
    public Account saveAccount(@RequestBody Account account) {
        return accountService.save(account);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAccount() {
        Account account = accountService.getCurrentAccount();
        accountService.delete(account);
        return ResponseEntity.status(HttpStatus.OK).body("Delete successfully");
    }
    @PostMapping("/savedbook")
    public List<RentingBook> saveBook(@RequestParam String barcode) {
        RentingBook rentingBook = bookService.findByBarcode(barcode);
        System.out.println(rentingBook);
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Account account = accountService.getAccount(username);
        List<RentingBook> savedBooks = account.getSavedBooks();
        if (savedBooks.contains(rentingBook)) {
            return savedBooks;
        }
        savedBooks.add(rentingBook);
        account.setSavedBooks(savedBooks);
        return accountService.save(account).getSavedBooks();
    }

    @GetMapping("/savedbook")
    public List<RentingBook> getSavedBooks() {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Account account = accountService.getAccount(username);
        return account.getSavedBooks();
    }

}
