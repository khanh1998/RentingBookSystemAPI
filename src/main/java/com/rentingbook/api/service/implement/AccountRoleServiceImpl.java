package com.rentingbook.api.service.implement;

import com.rentingbook.api.model.account.AccountRole;
import com.rentingbook.api.repository.AccountRoleRepository;
import com.rentingbook.api.service.AccountRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
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
    public AccountRole createAdminRole(String name) {
        AccountRole adminRole = accountRoleRepository.findByNameLike("ADMIN");
        if (adminRole == null) {
            adminRole = new AccountRole();
            adminRole.setName("ADMIN");
        }
        return adminRole;
    }

    @Override
    public AccountRole createCustomerRole(String name) {
        AccountRole customerRole = accountRoleRepository.findByNameLike("CUSTOMER");
        if (customerRole == null) {
            customerRole = new AccountRole();
            customerRole.setName("CUSTOMER");
        }
        return customerRole;
    }
}
