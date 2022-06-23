package com.example.bankingservice.service.impl;

import com.example.bankingservice.dto.TransactionDTO;
import com.example.bankingservice.entity.AccountEntity;
import com.example.bankingservice.entity.TransactionEntity;
import com.example.bankingservice.repository.AccountRepository;
import com.example.bankingservice.repository.TransactionRepository;
import com.example.bankingservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Override
    public boolean makeTransfer(TransactionDTO transactionDTO) {
        // TODO refactor synchronous implementation with messaging queue
        Long sourceAccountNumber = transactionDTO.getSourceAccountNumber();
        Optional<AccountEntity> sourceAccount = accountRepository
                .findById(sourceAccountNumber);

        Long targetAccountNumber = transactionDTO.getTargetAccountNumber();
        Optional<AccountEntity> targetAccount = accountRepository
                .findById(targetAccountNumber);

        if (sourceAccount.isPresent() && targetAccount.isPresent()) {
            if (isAmountAvailable(transactionDTO.getAmount(), sourceAccount.get().getAvailableBalance())) {
                TransactionEntity transaction = new TransactionEntity();
                transaction.setAmount(transactionDTO.getAmount());
                transaction.setSourceAccountNumber(sourceAccount.get().getAccountNumber());
                transaction.setTargetAccountNumber(targetAccount.get().getAccountNumber());
                transaction.setTargetOwnerName(targetAccount.get().getOwnerName());
                updateAccountBalance(sourceAccount.get(),targetAccount.get(), transactionDTO.getAmount());
                transactionRepository.save(transaction);
                return true;
            }
        }
        return false;
    }

    private void updateAccountBalance(AccountEntity sourceAccount, AccountEntity targetAccount, double amount) {
        sourceAccount.setAvailableBalance((sourceAccount.getAvailableBalance() - amount));
        accountRepository.save(sourceAccount);
        targetAccount.setAvailableBalance((targetAccount.getAvailableBalance() +amount));
        accountRepository.save(targetAccount);
    }

    // TODO support overdrafts or credit account
    private boolean isAmountAvailable(double amount, double accountBalance) {
        return (accountBalance - amount) > 0;
    }
}

