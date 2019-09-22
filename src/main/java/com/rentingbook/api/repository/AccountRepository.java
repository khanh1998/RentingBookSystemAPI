package com.rentingbook.api.repository;

import com.rentingbook.api.model.user.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByUsername(String username);
}
