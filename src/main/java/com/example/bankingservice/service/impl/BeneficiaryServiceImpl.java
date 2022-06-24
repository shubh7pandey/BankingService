package com.example.bankingservice.service.impl;

import com.example.bankingservice.dto.BeneficiaryDTO;
import com.example.bankingservice.entity.BeneficiaryEntity;
import com.example.bankingservice.repository.BeneficiaryRepository;
import com.example.bankingservice.service.BeneficiaryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {
    @Autowired
    private BeneficiaryRepository beneficiaryRepository;
    @Override
    public BeneficiaryDTO addBeneficiary(BeneficiaryDTO beneficiaryDTO) {
        BeneficiaryEntity bEntity = new BeneficiaryEntity();
        BeanUtils.copyProperties(beneficiaryDTO, bEntity);//all dto date will be set to entity
        bEntity = beneficiaryRepository.save(bEntity);
        BeanUtils.copyProperties(bEntity, beneficiaryDTO);//convert entity to dto
        return beneficiaryDTO;
    }
}
