package com.rentingbook.api.controller;

import com.rentingbook.api.model.order.BookOrder;
import com.rentingbook.api.model.user.Account;
import com.rentingbook.api.service.AccountService;
import com.rentingbook.api.service.BookOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account/order")
public class BookOrderController {
    private BookOrderService bookOrderService;
    private AccountService accountService;

    @Autowired
    public void setBookOrderService(BookOrderService bookOrderService) {
        this.bookOrderService = bookOrderService;
    }

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public BookOrder create(@RequestBody BookOrder bookOrder) {
        BookOrder order = bookOrderService.save(bookOrder);
        Account currentAccount = accountService.getCurrentAccount();
        List<BookOrder> bookOrders = currentAccount.getOrders();
        bookOrders.add(order);
        accountService.save(currentAccount);
        return order;
    }

    @GetMapping
    public BookOrder findOrder(@RequestParam Integer orderId) {
        Account currentAccount = accountService.getCurrentAccount();
        List<BookOrder> bookOrders = currentAccount.getOrders();
        return bookOrders.stream().filter(bookOrder -> bookOrder.getId() == orderId).findFirst().get();
    }
}
