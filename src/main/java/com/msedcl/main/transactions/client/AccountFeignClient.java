package com.msedcl.main.transactions.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.msedcl.main.transactions.common.ApiResponse;
import com.msedcl.main.transactions.dto.AccountResponseDto;
import com.msedcl.main.transactions.dto.BalanceUpdateRequestDto;

import jakarta.validation.Valid;
@FeignClient(name ="account-service",path="/accountapi",fallback = AccountFallback.class)
public interface AccountFeignClient {
	

	@GetMapping("accounts/account/{accountId}")
	public  ResponseEntity<ApiResponse<AccountResponseDto>> getAccountsListByAccountId(@Valid  @PathVariable("accountId") int accountId);
	
	@PutMapping("accounts/account")
	public  ResponseEntity<ApiResponse<AccountResponseDto>> updateBalance(@Valid @RequestBody BalanceUpdateRequestDto balanceUpdateDto);
	
}
