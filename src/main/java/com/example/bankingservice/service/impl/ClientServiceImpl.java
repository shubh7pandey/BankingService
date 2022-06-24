package com.example.bankingservice.service.impl;

import com.example.bankingservice.dto.ClientDTO;
import com.example.bankingservice.entity.AccountEntity;
import com.example.bankingservice.entity.ClientEntity;
import com.example.bankingservice.exception.BusinessException;
import com.example.bankingservice.exception.ErrorModel;
import com.example.bankingservice.repository.AccountRepository;
import com.example.bankingservice.repository.ClientRepository;
import com.example.bankingservice.service.ClientService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public ClientDTO register(ClientDTO clientDTO) {

        Optional<ClientEntity> optUe = clientRepository.findByOwnerEmail(clientDTO.getOwnerEmail());
        if(optUe.isPresent()){
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("EMAIL_ALREADY_EXIST");
            errorModel.setMessage("The Email With Which You Are Trying To Register Already Exist!");
            errorModelList.add(errorModel);
            throw new BusinessException(errorModelList);
        }

        ClientEntity clientEntity = new ClientEntity();
        BeanUtils.copyProperties(clientDTO, clientEntity);
        clientEntity = clientRepository.save(clientEntity);

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountType(clientDTO.getAccountType());
        accountEntity.setAvailableBalance(clientDTO.getAvailableBalance());
        accountEntity.setOwnerName(clientDTO.getOwnerName());
        accountEntity.setClientEntity(clientEntity);
        accountRepository.save(accountEntity);
        BeanUtils.copyProperties(clientEntity, clientDTO);

        return clientDTO;
    }

    @Override
    public ClientDTO login(String email, String password) {
        ClientDTO clientDTO = null;
        Optional<ClientEntity> optionalClientEntity = clientRepository.findByOwnerEmailAndPassword(email, password);

        if(optionalClientEntity.isPresent()){
            clientDTO = new ClientDTO();
            BeanUtils.copyProperties(optionalClientEntity.get(), clientDTO);
        }
        else{
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("INVALID_LOGIN");
            errorModel.setMessage("Incorrect Email or Password");
            errorModelList.add(errorModel);

            throw new BusinessException(errorModelList);
        }
        return clientDTO;
    }
}
