package com.example.bankingservice.repository;

import com.example.bankingservice.entity.ClientEntity;
import com.example.bankingservice.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    Optional<ClientEntity> findByOwnerEmailAndPassword(String email, String password);
    Optional<ClientEntity> findByOwnerEmail(String email);
}
