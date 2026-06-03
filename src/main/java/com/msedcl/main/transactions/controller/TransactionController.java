package com.msedcl.main.transactions.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msedcl.main.transactions.common.ApiResponse;
import com.msedcl.main.transactions.dto.TransactionRequestDto;
import com.msedcl.main.transactions.dto.TransactionResponseDto;
import com.msedcl.main.transactions.service.TransactionService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@RestController
@Slf4j
@Validated
@RequestMapping("transactionsapi")
public class TransactionController {

	private TransactionService transactionService;
	private static final String TRANSACTION_DEPOSIT="deposit";
	private static final String TRANSACTION_WITHDRAW="withdraw";

	@PostMapping("transactions/transaction/deposit")
	public ResponseEntity<ApiResponse<TransactionResponseDto>> createDepositTrasaction(
			@RequestBody @Valid TransactionRequestDto transactionRequestDto) {
		log.info("Transaction Controller- Create New Transaction");
		log.info("Transaction input Request: "+transactionRequestDto);
		TransactionResponseDto transactionResponseDto=transactionService.createTransaction(transactionRequestDto,TRANSACTION_DEPOSIT);
		
		
		ApiResponse<TransactionResponseDto> apiResponse = new ApiResponse<TransactionResponseDto>("CREATED",
				"New Transaction Created of type "+TRANSACTION_DEPOSIT, transactionResponseDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
	}
	
	@PostMapping("transactions/transaction/withdraw")
	public ResponseEntity<ApiResponse<TransactionResponseDto>> createWithdrawTrasaction(
			@RequestBody @Valid TransactionRequestDto transactionRequestDto) {
		log.info("Transaction Controller- Create New Transaction");
		log.info("Transaction input Request: "+transactionRequestDto);
		TransactionResponseDto transactionResponseDto=transactionService.createTransaction(transactionRequestDto,TRANSACTION_WITHDRAW);
		
		
		ApiResponse<TransactionResponseDto> apiResponse = new ApiResponse<TransactionResponseDto>("CREATED",
				"New Transaction Created of type "+TRANSACTION_WITHDRAW, transactionResponseDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
	}
	
	@GetMapping("transactions/transaction/{accountId}")
	public ResponseEntity<ApiResponse<List<TransactionResponseDto>>> getAllTransactionsByAccountId(@PathVariable  int accountId){
		log.info("Transaction Controller- Get All transactions by account id");
		List<TransactionResponseDto> listAllTransaction=new ArrayList<TransactionResponseDto>();
		listAllTransaction=transactionService.allTransactionsByAccountId(accountId);
		ApiResponse<List<TransactionResponseDto>> apiResponse = new ApiResponse<List<TransactionResponseDto>>(
				"ALL",
				"List of all transaction by account id:  "+accountId, 
				listAllTransaction);
		return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	}

}
