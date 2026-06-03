package com.msedcl.main.transactions.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDto {	
	private int transactionId;
	private AccountResponseDto account;	
	private String transactionType;
	private double transactionAmount;
	private Date transactionDate;
	

}
