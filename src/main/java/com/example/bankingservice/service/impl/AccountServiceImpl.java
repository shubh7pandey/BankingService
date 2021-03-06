package com.example.bankingservice.service.impl;

import com.example.bankingservice.dto.AccountDTO;
import com.example.bankingservice.entity.AccountEntity;
import com.example.bankingservice.repository.AccountRepository;
import com.example.bankingservice.service.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public AccountDTO getAccountDetails(Long accountNumber) {
        Optional<AccountEntity> optAccount = accountRepository.findById(accountNumber);
        AccountDTO accountDTO = null;
        if(optAccount.isPresent()){
            accountDTO = new AccountDTO();
            BeanUtils.copyProperties(optAccount.get(), accountDTO);
        }
        return accountDTO;
    }

    @Override
    public AccountDTO updateAccountBalance(AccountDTO accountDTO, Long accountNumber) {
        Optional<AccountEntity> optEntity = accountRepository.findById(accountNumber);
        AccountEntity accEntity = null;
        if(optEntity.isPresent()){
            accEntity = optEntity.get();
            accEntity.setAvailableBalance(accountDTO.getAvailableBalance());
            accEntity = accountRepository.save(accEntity);
        }
        BeanUtils.copyProperties(accEntity, accountDTO);
        return accountDTO;
    }
}
