package com.rentingbook.api.controller;

import com.rentingbook.api.model.account.Account;
import com.rentingbook.api.model.book.RentalBook;
import com.rentingbook.api.model.book.RentedBook;
import com.rentingbook.api.model.cart.Cart;
import com.rentingbook.api.model.order.BookOrder;
import com.rentingbook.api.model.order.ReturnBookOrder;
import com.rentingbook.api.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {
    private AccountService accountService;
    private RentalBookService bookService;
    private RentedBookService rentedBookService;
    private CartService cartService;
    private ReturnBookOrderService returnBookOrderService;

    @Autowired
    public void setReturnBookOrderService(ReturnBookOrderService returnBookOrderService) {
        this.returnBookOrderService = returnBookOrderService;
    }

    @Autowired
    public void setRentedBookService(RentedBookService rentedBookService) {
        this.rentedBookService = rentedBookService;
    }

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Autowired
    public void setBookService(RentalBookService bookService) {
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
        cart = cartService.save(cart);
        account.setCart(cart);
        account.setSavedBooks(new ArrayList<>());
        account.setCurrentRentalBook(new ArrayList<>());
        account.setOrders(new ArrayList<>());
        account.setReturnBookOrders(new ArrayList<>());
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.createAccount(account));
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
    public List<RentalBook> saveBook(@RequestParam String barcode) {
        RentalBook rentalBook = bookService.findByBarcode(barcode);
        System.out.println(rentalBook);
        Account account = accountService.getCurrentAccount();
        List<RentalBook> savedBooks = account.getSavedBooks();
        if (savedBooks.contains(rentalBook)) {
            return savedBooks;
        }
        savedBooks.add(rentalBook);
        account.setSavedBooks(savedBooks);
        return accountService.save(account).getSavedBooks();
    }

    @PostMapping("/rentedbook")
    public ResponseEntity<List<RentedBook>> addRentedBook(@RequestParam int orderId) {
        //get current rented books of account
        Account account = accountService.getCurrentAccount();
        List<RentedBook> rentedBookList = account.getCurrentRentalBook();

        //get order of account
        Optional<BookOrder> order = account.getOrders()
                .stream()
                .filter(bookOrder -> bookOrder.getId() == orderId)
                .findFirst();
        if (order.isPresent()) {
            //get rental book list from order
            List<RentalBook> rentalBookList = order.get().getBooks();
            //convert rental book list to rented book list
            List<RentedBook> rentedBookListInput = new ArrayList<>();
            rentalBookList.forEach(rentalBook -> rentedBookListInput.add(new RentedBook(rentalBook, order.get().getDeliveredDate(), 0)));
            //save rented book list
            rentedBookService.save(rentedBookListInput);
            System.out.println(rentedBookListInput);
            rentedBookList.addAll(rentedBookListInput);
            System.out.println(rentedBookList);
            //set rented book list for account again
            account.setCurrentRentalBook(rentedBookList);
            return ResponseEntity.ok(accountService.save(account).getCurrentRentalBook());
        }

        return ResponseEntity.badRequest().body(null);
    }

    @DeleteMapping("rentedbook")
    public ResponseEntity<Account> deleteRentedBook(@RequestParam int returnOrderId) {
        Optional<ReturnBookOrder> returnBookOrder = returnBookOrderService.findOne(returnOrderId);
        Account account = accountService.getCurrentAccount();
        if (returnBookOrder.isPresent()) {
            //delete book in current rental book list
            returnBookOrder
                    .get()
                    .getRentedBooks()
                    .forEach(rentedBook -> account
                            .getCurrentRentalBook()
                            .removeIf(rentedBook1 -> rentedBook1.getId() == rentedBook.getId())
                    );
            return ResponseEntity.ok().body(accountService.save(account));
        }
        return ResponseEntity.badRequest().body(null);
    }
    @GetMapping("/savedbook")
    public List<RentalBook> getSavedBooks() {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Account account = accountService.getAccount(username);
        return account.getSavedBooks();
    }

}
