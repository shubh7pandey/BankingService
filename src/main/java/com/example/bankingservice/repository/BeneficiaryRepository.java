package com.example.bankingservice.repository;

import com.example.bankingservice.entity.AccountEntity;
import com.example.bankingservice.entity.BeneficiaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BeneficiaryRepository extends JpaRepository<BeneficiaryEntity, Long> {
}

