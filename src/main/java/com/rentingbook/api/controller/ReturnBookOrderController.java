package com.rentingbook.api.controller;

import com.rentingbook.api.model.account.Account;
import com.rentingbook.api.model.order.ReturnBookOrder;
import com.rentingbook.api.service.AccountService;
import com.rentingbook.api.service.ReturnBookOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/returnbookorder")
public class ReturnBookOrderController {
    private ReturnBookOrderService returnBookOrderService;
    private AccountService accountService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Autowired
    public void setReturnBookOrderService(ReturnBookOrderService returnBookOrderService) {
        this.returnBookOrderService = returnBookOrderService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ReturnBookOrder returnBookOrder) {
        ReturnBookOrder order = returnBookOrderService.save(returnBookOrder);
        Account account = accountService.getCurrentAccount();
        if (account.getUsername().equalsIgnoreCase(order.getAccount())) {
            List<ReturnBookOrder> list = account.getReturnBookOrders();
            list.add(returnBookOrder);
            account.setReturnBookOrders(list);

            accountService.save(account);
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.badRequest().body("something went wrong");
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestParam int returnBookOrderId) {
        Optional<ReturnBookOrder> returnBookOrder = returnBookOrderService.findOne(returnBookOrderId);
        if (returnBookOrder.isPresent()) {
            returnBookOrder.get().setCancel(true);
            ReturnBookOrder saved = returnBookOrderService.save(returnBookOrder.get());
            return ResponseEntity.ok(saved);
        }
        return ResponseEntity.badRequest().body("Return book order ID is not true");
    }

}
