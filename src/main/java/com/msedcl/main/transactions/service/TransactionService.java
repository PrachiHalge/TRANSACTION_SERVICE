package com.msedcl.main.transactions.service;

import java.util.List;

import com.msedcl.main.transactions.dto.TransactionRequestDto;
import com.msedcl.main.transactions.dto.TransactionResponseDto;

public interface TransactionService {	
	
	public TransactionResponseDto createTransaction(TransactionRequestDto transactionRequestDto,String transactionType);
	
	public List<TransactionResponseDto> allTransactionsByAccountId(int accountId) ;
	

}
