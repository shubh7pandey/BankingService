package com.example.bankingservice.controller;

import com.example.bankingservice.dto.ClientDTO;
import com.example.bankingservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/register")
    public ResponseEntity<ClientDTO> register(@Valid @RequestBody ClientDTO clientDTO){
        clientDTO = clientService.register(clientDTO);
        return new ResponseEntity<>(clientDTO, HttpStatus.CREATED);
    }

    @PostMapping(path = "/login", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<ClientDTO> login(@Valid @RequestBody ClientDTO clientDTO){
        clientDTO = clientService.login(clientDTO.getOwnerEmail(), clientDTO.getPassword());
        return new ResponseEntity<>(clientDTO, HttpStatus.OK);
    }
}

