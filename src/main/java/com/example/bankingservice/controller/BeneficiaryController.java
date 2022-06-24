package com.example.bankingservice.controller;

import com.example.bankingservice.dto.BeneficiaryDTO;
import com.example.bankingservice.service.BeneficiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/beneficiary")
public class BeneficiaryController {
    @Autowired//spring plz make the object of bsimpl available to bs interface reference here in controller
    private BeneficiaryService beneficiaryService;

    @PostMapping("/account")
    public ResponseEntity<BeneficiaryDTO> addBook(@RequestBody BeneficiaryDTO beneficiaryDTO){
        beneficiaryDTO = beneficiaryService.addBeneficiary(beneficiaryDTO);
        ResponseEntity<BeneficiaryDTO> responseEntity = new ResponseEntity<>(beneficiaryDTO, HttpStatus.CREATED);
        return responseEntity;
    }
}
