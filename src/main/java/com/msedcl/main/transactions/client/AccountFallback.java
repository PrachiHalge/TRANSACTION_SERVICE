package com.msedcl.main.transactions.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.msedcl.main.transactions.common.ApiResponse;
import com.msedcl.main.transactions.dto.AccountResponseDto;
import com.msedcl.main.transactions.dto.BalanceUpdateRequestDto;

import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class AccountFallback implements AccountFeignClient {

	@Override
	public ResponseEntity<ApiResponse<AccountResponseDto>> getAccountsListByAccountId( int accountId) {
		log.info("Account service is DOWN. Fallback executed for id: {}", accountId);
		ApiResponse<AccountResponseDto> response = new ApiResponse<>("FAILOVER", "Accounts service unavailable", null);
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
	}

	@Override
	public ResponseEntity<ApiResponse<AccountResponseDto>> updateBalance(
			 BalanceUpdateRequestDto balanceUpdateDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
