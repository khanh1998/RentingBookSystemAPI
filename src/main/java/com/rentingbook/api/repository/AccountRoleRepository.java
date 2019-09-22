package com.rentingbook.api.repository;

import com.rentingbook.api.model.user.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRoleRepository extends JpaRepository<AccountRole, Integer> {
}
