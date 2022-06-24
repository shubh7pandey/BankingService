package com.example.bankingservice.controller;

import com.example.bankingservice.dto.AccountDTO;
import com.example.bankingservice.dto.ClientDTO;
import com.example.bankingservice.dto.TransactionDTO;
import com.example.bankingservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/account")
public class TransactionController {
    private final String Response_Message="Transfer has been done successfully";
    @Autowired
    private TransactionService transactionService;
    @PostMapping("/transaction")
    public ResponseEntity<?> makeTransfer(@Valid @RequestBody TransactionDTO transactionDTO){
        transactionService.makeTransfer(transactionDTO);
        return new ResponseEntity<>(Response_Message, HttpStatus.CREATED);
    }
    @PostMapping("/transaction/updateBalance")
    public ResponseEntity<?> updateBalance(@RequestBody TransactionDTO transactionDTO){
        transactionService.updateBalance(transactionDTO);
        return new ResponseEntity<>(Response_Message, HttpStatus.OK);
    }
}
