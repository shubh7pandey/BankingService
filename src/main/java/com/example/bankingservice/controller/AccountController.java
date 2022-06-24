package com.example.bankingservice.controller;

import com.example.bankingservice.dto.AccountDTO;
import com.example.bankingservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable Long accountNumber){
        AccountDTO accountDTO = accountService.getAccountDetails(accountNumber);
        ResponseEntity<AccountDTO> responseEntity = new ResponseEntity<>(accountDTO, HttpStatus.OK);
        return responseEntity;
    }
    @PatchMapping("/updateBalance/{accountNumber}")
    public ResponseEntity<AccountDTO> updateAccountBalance(@RequestBody AccountDTO accountDTO, @PathVariable Long accountNumber){
        accountDTO = accountService.updateAccountBalance(accountDTO, accountNumber);
        ResponseEntity<AccountDTO> responseEntity = new ResponseEntity<>(accountDTO, HttpStatus.OK);
        return responseEntity;
    }
}
