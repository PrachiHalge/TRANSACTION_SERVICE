package com.msedcl.main.transactions.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.msedcl.main.transactions.client.AccountRESTClient;
import com.msedcl.main.transactions.dto.AccountResponseDto;
import com.msedcl.main.transactions.dto.BalanceUpdateRequestDto;
import com.msedcl.main.transactions.dto.TransactionRequestDto;
import com.msedcl.main.transactions.dto.TransactionResponseDto;
import com.msedcl.main.transactions.entity.Transaction;
import com.msedcl.main.transactions.exception.AccountNotFoundException;
import com.msedcl.main.transactions.mapper.TransactionMapper;
import com.msedcl.main.transactions.repository.TransactionRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Service
@AllArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {
	private TransactionRepository  transactionRepository;
	private final AccountRESTClient accountRESTClient;
	private static final String TRANSACTION_DEPOSIT="deposit";
	private static final String TRANSACTION_WITHDRAW="withdraw";
	
	@Override
	public TransactionResponseDto createTransaction(TransactionRequestDto transactionRequestDto,String transactionType) {
		log.info("TransactionService-createNewTransaction");
		log.info("Fetch Account from account id from Account service");
		AccountResponseDto accountResponseDto = accountRESTClient
				.getAccountByAccountId(transactionRequestDto.getAccountId());
		log.info("Account Details fetched from account service are: " + accountResponseDto.toString());
		
		//save transaction
		log.info("Saving new transaction details");
		Transaction savedTxn=saveTransaction(transactionRequestDto,accountResponseDto,transactionType);
		
		
		if(savedTxn!=null) {
			log.info("TransactionService: Update balance after transaction save");
			accountResponseDto=updateBalance(transactionRequestDto,accountResponseDto,transactionType);			
		}
		return TransactionMapper.toDto(savedTxn, accountResponseDto);	
		
		}

		
	private AccountResponseDto updateBalance(TransactionRequestDto transactionRequestDto,
			AccountResponseDto accountResponseDto, String transactionType) {
		BalanceUpdateRequestDto  balanceUpdateRequestDto=new BalanceUpdateRequestDto();
		balanceUpdateRequestDto.setAccountId(accountResponseDto.getAccountId());
		balanceUpdateRequestDto.setAmount(transactionRequestDto.getTransactionAmount());
		balanceUpdateRequestDto.setTransactionType(transactionType);
		log.info("Calling AccountService,to Update balance");
		accountResponseDto=accountRESTClient.updateBalance(balanceUpdateRequestDto);
		return accountResponseDto;
	}


	private Transaction saveTransaction(TransactionRequestDto transactionRequestDto,
			AccountResponseDto accountResponseDto, String transactionType) {
		Transaction savedTxn = new Transaction();
		if (accountResponseDto != null) {
			savedTxn.setTransactionAmount(transactionRequestDto.getTransactionAmount());
			savedTxn.setTransactionDate(new Date());
			savedTxn.setTransactionType(transactionType);
			savedTxn.setAccountId(accountResponseDto.getAccountId());
			savedTxn = transactionRepository.save(savedTxn);

			log.info("Transaction Details Saved");
			log.info(savedTxn.toString());
			return savedTxn;
		}
		throw new AccountNotFoundException("Account Details Invalid");

	}
	
	@Override
	public List<TransactionResponseDto> allTransactionsByAccountId(int accountId) {
		log.info("TransactionService: Get all transactions by Account Id"); 
	    List<TransactionResponseDto> allTransactions = new ArrayList<>();

	    List<Transaction> txnList =
	            transactionRepository.findByaccountId(accountId);

	    txnList.forEach(t -> {

	        AccountResponseDto accountResponseDto =
	                accountRESTClient.getAccountByAccountId(t.getAccountId());

	        allTransactions.add(
	                TransactionMapper.toDto(t, accountResponseDto)
	        );
	    });

	    return allTransactions;
	}
}
