package com.example.bankingservice.service;

import com.example.bankingservice.dto.BeneficiaryDTO;

import java.util.List;

public interface BeneficiaryService {
    BeneficiaryDTO addBeneficiary(BeneficiaryDTO beneficiaryDTO);
    BeneficiaryDTO getBeneficiary(Long accountNumber);
    List<BeneficiaryDTO> getAllBeneficiary();
    void deleteBeneficiary(Long accountNumber);
}
