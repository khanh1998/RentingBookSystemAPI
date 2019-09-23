package com.rentingbook.api.service.implement;

import com.rentingbook.api.model.account.AccountRole;
import com.rentingbook.api.repository.AccountRoleRepository;
import com.rentingbook.api.service.AccountRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountRoleServiceImpl implements AccountRoleService {
    private AccountRoleRepository accountRoleRepository;

    @Autowired
    public void setAccountRoleRepository(AccountRoleRepository accountRoleRepository) {
        this.accountRoleRepository = accountRoleRepository;
    }

    @Override
    public AccountRole getRole(int id) {
        return accountRoleRepository.findById(id).get();
    }

    @Override
    public AccountRole createRole(String name) {
        AccountRole accountRole = new AccountRole();
        accountRole.setName("ADMIN");
        return accountRoleRepository.save(accountRole);
    }
}
