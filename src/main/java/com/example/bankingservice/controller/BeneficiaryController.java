package com.example.bankingservice.controller;

import com.example.bankingservice.dto.AccountDTO;
import com.example.bankingservice.dto.BeneficiaryDTO;
import com.example.bankingservice.service.BeneficiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/beneficiary")
public class BeneficiaryController {
    @Autowired//spring plz make the object of bsimpl available to bs interface reference here in controller
    private BeneficiaryService beneficiaryService;
    @GetMapping("/{accountNumber}")
    public ResponseEntity<BeneficiaryDTO> getAccount(@PathVariable Long accountNumber){
        BeneficiaryDTO beneficiaryDTO = beneficiaryService.getBeneficiary(accountNumber);
        ResponseEntity<BeneficiaryDTO> responseEntity = new ResponseEntity<>(beneficiaryDTO, HttpStatus.OK);
        return responseEntity;
    }
    @GetMapping("/")
    public ResponseEntity<List<BeneficiaryDTO>> getAllBeneficiary(){
        List<BeneficiaryDTO> beneficiaries = beneficiaryService.getAllBeneficiary();
        ResponseEntity<List<BeneficiaryDTO>> responseEntity = new ResponseEntity<>(beneficiaries, HttpStatus.OK);
        //responseEntity = ResponseEntity.ok(books);
        return responseEntity;
    }

    @PostMapping("/account")
    public ResponseEntity<BeneficiaryDTO> addBook(@RequestBody BeneficiaryDTO beneficiaryDTO){
        beneficiaryDTO = beneficiaryService.addBeneficiary(beneficiaryDTO);
        ResponseEntity<BeneficiaryDTO> responseEntity = new ResponseEntity<>(beneficiaryDTO, HttpStatus.CREATED);
        return responseEntity;
    }
    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long accountNumber){
        beneficiaryService.deleteBeneficiary(accountNumber);
        ResponseEntity<?> responseEntity = new ResponseEntity<>("Account Deleted Successfully", HttpStatus.OK);
        return responseEntity;
    }
}
