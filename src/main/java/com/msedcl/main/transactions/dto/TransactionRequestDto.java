package com.msedcl.main.transactions.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDto {
	@NotNull(message="Account Id can not be null")	
	private int accountId;
	
	@NotNull(message="Transaction amount can not be null")	
	private double transactionAmount;
}
