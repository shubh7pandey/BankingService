package com.example.bankingservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BENEFICIARY_TABLE")
@Getter
@Setter
@NoArgsConstructor
public class BeneficiaryEntity {
    @Id
    private Long accountNumber;
    private String ownerName;
    private String bankName;
    private Long ifscCode;
}

