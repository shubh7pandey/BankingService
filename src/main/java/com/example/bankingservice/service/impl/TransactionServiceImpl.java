package com.example.bankingservice.service.impl;

import com.example.bankingservice.dto.TransactionDTO;
import com.example.bankingservice.entity.AccountEntity;
import com.example.bankingservice.entity.BeneficiaryEntity;
import com.example.bankingservice.entity.TransactionEntity;
import com.example.bankingservice.exception.BusinessException;
import com.example.bankingservice.exception.ErrorModel;
import com.example.bankingservice.repository.AccountRepository;
import com.example.bankingservice.repository.BeneficiaryRepository;
import com.example.bankingservice.repository.TransactionRepository;
import com.example.bankingservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private BeneficiaryRepository beneficiaryRepository;
    @Override
    public boolean makeTransfer(TransactionDTO transactionDTO) {
        // TODO refactor synchronous implementation with messaging queue
        Long sourceAccountNumber = transactionDTO.getSourceAccountNumber();
        Optional<AccountEntity> sourceAccount = accountRepository
                .findById(sourceAccountNumber);

        Long targetAccountNumber = transactionDTO.getTargetAccountNumber();
        Optional<BeneficiaryEntity> targetAccount = beneficiaryRepository
                .findById(targetAccountNumber);

        if (sourceAccount.isPresent() && targetAccount.isPresent()) {
            if (isAmountAvailable(transactionDTO.getAmount(), sourceAccount.get().getAvailableBalance())) {
                TransactionEntity transaction = new TransactionEntity();
                transaction.setAmount(transactionDTO.getAmount());
                transaction.setSourceAccountNumber(sourceAccount.get().getAccountNumber());
                transaction.setTargetAccountNumber(targetAccount.get().getAccountNumber());
                transaction.setTargetOwnerName(targetAccount.get().getOwnerName());
                updateAccountBalance(sourceAccount.get(), transactionDTO.getAmount());
                transactionRepository.save(transaction);
                return true;
            }
        }
        else{
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("INVALID_TRANSACTION");
            errorModel.setMessage("Incorrect Transaction Details");
            errorModelList.add(errorModel);
            throw new BusinessException(errorModelList);
        }
        return false;
    }

    @Override
    public boolean updateBalance(TransactionDTO transactionDTO) {
        Long sourceAccountNumber = transactionDTO.getSourceAccountNumber();
        Optional<AccountEntity> sourceAccount = accountRepository
                .findById(sourceAccountNumber);

        if (sourceAccount.isPresent()) {
                TransactionEntity transaction = new TransactionEntity();
                transaction.setAmount(transactionDTO.getAmount());
                transaction.setSourceAccountNumber(sourceAccount.get().getAccountNumber());
                updateAccountBalance(sourceAccount.get(), -transactionDTO.getAmount());
                transactionRepository.save(transaction);
                return true;
        }
        else{
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("INVALID_TRANSACTION");
            errorModel.setMessage("Incorrect Transaction Details");
            errorModelList.add(errorModel);
            throw new BusinessException(errorModelList);
        }
    }
    private void updateAccountBalance(AccountEntity sourceAccount, double amount) {
        sourceAccount.setAvailableBalance((sourceAccount.getAvailableBalance() - amount));
        accountRepository.save(sourceAccount);
    }

    // TODO support overdrafts or credit account
    private boolean isAmountAvailable(double amount, double accountBalance) {
        return (accountBalance - amount) > 0;
    }
}
