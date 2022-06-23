package com.example.bankingservice.service;

import com.example.bankingservice.dto.ClientDTO;

public interface ClientService {
    ClientDTO register(ClientDTO clientDTO);
    ClientDTO login(String email, String password);
}
