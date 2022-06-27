package com.example.bankingservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientDTO {
    private Long id;
    private String ownerName;
    @NotNull
    @NotEmpty(message = "Owner Email cannot be empty")
    @Size(min = 1, max = 50, message = "Owner Email should be between 1 to 50 characters in length")
    private String ownerEmail;
    private String phone;
    @NotNull
    @NotEmpty(message = "Password cannot be empty")
    private String password;
    private String accountNumber;
    private String accountType;
    private Double availableBalance;
}
