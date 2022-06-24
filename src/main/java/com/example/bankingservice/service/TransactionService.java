package com.example.bankingservice.service;

import com.example.bankingservice.dto.TransactionDTO;

public interface TransactionService {
    boolean makeTransfer(TransactionDTO transactionDTO);
    boolean updateBalance(TransactionDTO transactionDTO);
}
