package com.msedcl.main.transactions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BalanceUpdateRequestDto {
	private int accountId;
	private double amount;
	private String transactionType;

}
