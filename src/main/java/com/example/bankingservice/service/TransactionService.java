package com.example.bankingservice.service;

import com.example.bankingservice.dto.BeneficiaryDTO;
import com.example.bankingservice.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {
    boolean makeTransfer(TransactionDTO transactionDTO);
    boolean updateBalance(TransactionDTO transactionDTO);
    List<TransactionDTO> getAllTransaction();
}
