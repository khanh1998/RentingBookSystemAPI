package com.rentingbook.api.service;

import com.rentingbook.api.model.user.Account;

public interface AccountService {
    Account getAccount(String username);

    Account createAccount(Account account);
}
