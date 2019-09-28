package com.rentingbook.api.service;

import com.rentingbook.api.model.user.Account;

public interface AccountService {
    Account getAccount(String username);

    Account save(Account account);
    Account createAccount(Account account);

    Account getCurrentAccount();
}
