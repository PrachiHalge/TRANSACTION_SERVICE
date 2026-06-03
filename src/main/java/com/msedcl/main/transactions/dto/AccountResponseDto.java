package com.msedcl.main.transactions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponseDto {
	private int accountId;
	private CustomerResponseDto customer;
	private String accountType;
	private double balance;
}
