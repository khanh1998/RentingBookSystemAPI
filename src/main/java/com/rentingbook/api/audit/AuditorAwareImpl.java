package com.rentingbook.api.audit;

import com.rentingbook.api.model.account.Account;
import com.rentingbook.api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Account> {
    private AccountService accountService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public Optional<Account> getCurrentAuditor() {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        System.out.println("username:" + username);
        Account account = accountService.getAccount(username);
        if (account != null) {
            return Optional.of(account);
        }
        return Optional.empty();
    }
}
