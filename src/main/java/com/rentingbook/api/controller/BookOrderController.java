package com.rentingbook.api.controller;

import com.rentingbook.api.model.account.Account;
import com.rentingbook.api.model.order.BookOrder;
import com.rentingbook.api.model.order.orderdetails.OrderStatus;
import com.rentingbook.api.service.AccountService;
import com.rentingbook.api.service.BookOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<BookOrder> create(@RequestBody BookOrder bookOrder) {
        BookOrder order = bookOrderService.save(bookOrder);
        Account currentAccount = accountService.getCurrentAccount();
        if (currentAccount.getUsername().equalsIgnoreCase(bookOrder.getAccount())) {
            List<BookOrder> bookOrders = currentAccount.getOrders();
            bookOrders.add(order);
            Account saved = accountService.save(currentAccount);
            return ResponseEntity.of(saved.getOrders().stream().findFirst());
        }
        return null;
    }

    @PatchMapping
    public ResponseEntity<?> updateOrder(@RequestBody BookOrder bookOrder) {
        if (bookOrder.getStatus() != OrderStatus.Done) {
            bookOrder.setCancel(true);
            return ResponseEntity.ok(bookOrderService.save(bookOrder));
        }
        return ResponseEntity.badRequest().body("You can not update order when it has been done");
    }
    @GetMapping
    public ResponseEntity<BookOrder> findOrder(@RequestParam Integer orderId) {
        Account currentAccount = accountService.getCurrentAccount();
        List<BookOrder> bookOrders = currentAccount.getOrders();
        Optional<BookOrder> savedOrder = bookOrders.stream().filter(bookOrder -> bookOrder.getId() == orderId).findFirst();
        return ResponseEntity.of(savedOrder);
    }
}
