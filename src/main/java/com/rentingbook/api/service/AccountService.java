package com.rentingbook.api.service;

import com.rentingbook.api.model.account.Account;

public interface AccountService {
    Account getAccount(String username);

    Account save(Account account);
    Account createAccount(Account account);

    void delete(Account account);
    Account getCurrentAccount();
}
