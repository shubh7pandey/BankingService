package com.example.bankingservice.repository;

import com.example.bankingservice.entity.AccountEntity;
import com.example.bankingservice.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
}
