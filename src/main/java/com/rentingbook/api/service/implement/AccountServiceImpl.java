package com.rentingbook.api.service.implement;

import com.rentingbook.api.model.user.Account;
import com.rentingbook.api.model.user.AccountRole;
import com.rentingbook.api.repository.AccountRepository;
import com.rentingbook.api.service.AccountRoleService;
import com.rentingbook.api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;
    private AccountRoleService accountRoleService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Autowired
    public void setAccountRoleService(AccountRoleService accountRoleService) {
        this.accountRoleService = accountRoleService;
    }

    @Override
    public Account getAccount(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account createAccount(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        AccountRole accountRole = accountRoleService.createRole("ADMIN");
        account.setAccountRole(accountRole);
        return accountRepository.save(account);
    }

    @Override
    public Account getCurrentAccount() {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return accountRepository.findByUsername(username);
    }
}
