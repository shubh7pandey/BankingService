package com.example.bankingservice.entity;

import com.example.bankingservice.dto.ClientDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ACCOUNT_TABLE")
@Getter
@Setter
@NoArgsConstructor
public class AccountEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long accountNumber;
    private String accountType;
    private Double availableBalance;
    private String ownerName;
    @OneToOne
    @JoinColumn(name = "CLIENT_ID", nullable = false)
    private ClientEntity clientEntity;
}
