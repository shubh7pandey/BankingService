package com.example.bankingservice.service;

import com.example.bankingservice.dto.AccountDTO;

public interface AccountService {
    public AccountDTO getAccountDetails(Long accountNumber);
    public AccountDTO updateAccountBalance(AccountDTO accountDTO, Long accountNumber);
}
