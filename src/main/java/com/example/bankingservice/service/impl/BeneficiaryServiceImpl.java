package com.example.bankingservice.service.impl;

import ch.qos.logback.classic.jmx.MBeanUtil;
import com.example.bankingservice.dto.AccountDTO;
import com.example.bankingservice.dto.BeneficiaryDTO;
import com.example.bankingservice.entity.AccountEntity;
import com.example.bankingservice.entity.BeneficiaryEntity;
import com.example.bankingservice.exception.BusinessException;
import com.example.bankingservice.exception.ErrorModel;
import com.example.bankingservice.repository.BeneficiaryRepository;
import com.example.bankingservice.service.BeneficiaryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public BeneficiaryDTO getBeneficiary(Long accountNumber) {
        Optional<BeneficiaryEntity> optAccount = beneficiaryRepository.findById(accountNumber);
        BeneficiaryDTO beneficiaryDTO = null;
        if(optAccount.isPresent()){
            beneficiaryDTO = new BeneficiaryDTO();
            BeanUtils.copyProperties(optAccount.get(), beneficiaryDTO);
        }
        return beneficiaryDTO;
    }

    @Override
    public List<BeneficiaryDTO> getAllBeneficiary() {

        List<BeneficiaryEntity> beneficiaryEntities = beneficiaryRepository.findAll();
        List<BeneficiaryDTO> beneficiaryDtos = null;

        if(beneficiaryEntities != null && !beneficiaryEntities.isEmpty()){// not null & not empty
            beneficiaryDtos = new ArrayList<>();
            BeneficiaryDTO dto = null;
            for(BeneficiaryEntity be : beneficiaryEntities){
                dto = new BeneficiaryDTO();
                BeanUtils.copyProperties(be, dto);
                beneficiaryDtos.add(dto);
            }
        }
        return beneficiaryDtos;
    }

    @Override
    public void deleteBeneficiary(Long accountNumber) {
        Optional<BeneficiaryEntity> bEntity = beneficiaryRepository.findById(accountNumber);
        if(bEntity.isPresent()){
            beneficiaryRepository.deleteById(accountNumber);
        }
        else{
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("NO_ACCOUNT_FOUND");
            errorModel.setMessage("Account Doesn't Exist!");
            errorModelList.add(errorModel);
            throw new BusinessException(errorModelList);
        }
    }
}
