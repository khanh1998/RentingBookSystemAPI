package com.rentingbook.api.service;

import com.rentingbook.api.model.account.AccountRole;

public interface AccountRoleService {
    AccountRole getRole(int id);

    AccountRole createAdminRole(String name);

    AccountRole createCustomerRole(String name);
}
