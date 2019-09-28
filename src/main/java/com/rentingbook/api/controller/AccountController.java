package com.rentingbook.api.controller;

import com.rentingbook.api.model.book.RentingBook;
import com.rentingbook.api.model.user.Account;
import com.rentingbook.api.service.AccountService;
import com.rentingbook.api.service.RentingBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    private AccountService accountService;
    private RentingBookService bookService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Autowired
    public void setBookService(RentingBookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("")
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
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
