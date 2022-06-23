package com.example.bankingservice.controller;

import com.example.bankingservice.dto.AccountDTO;
import com.example.bankingservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountDTO> getBook(@PathVariable Long accountNumber){
        AccountDTO accountDTO = accountService.getAccountDetails(accountNumber);
        ResponseEntity<AccountDTO> responseEntity = new ResponseEntity<>(accountDTO, HttpStatus.OK);
        return responseEntity;
    }

}
