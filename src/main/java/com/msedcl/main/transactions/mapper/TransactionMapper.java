package com.msedcl.main.transactions.mapper;

import com.msedcl.main.transactions.dto.AccountResponseDto;
import com.msedcl.main.transactions.dto.TransactionRequestDto;
import com.msedcl.main.transactions.dto.TransactionResponseDto;
import com.msedcl.main.transactions.entity.Transaction;

public class TransactionMapper {
	public static Transaction toEntity(TransactionRequestDto transactionRequestDto) {
		Transaction txn=new Transaction();
		txn.setAccountId(transactionRequestDto.getAccountId());
		txn.setTransactionAmount(transactionRequestDto.getTransactionAmount());
		return txn;
	}
	
public static TransactionResponseDto toDto(Transaction transaction,AccountResponseDto accountResponseDto) {
		TransactionResponseDto txnResponseDto=new TransactionResponseDto();
		txnResponseDto.setTransactionId(transaction.getTransactionId());
		txnResponseDto.setTransactionDate(transaction.getTransactionDate());
		txnResponseDto.setTransactionType(transaction.getTransactionType());
		txnResponseDto.setTransactionAmount(transaction.getTransactionAmount());
		txnResponseDto.setAccount(accountResponseDto);
		return txnResponseDto;
	}

}
