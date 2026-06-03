package com.msedcl.main.transactions.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.msedcl.main.transactions.common.ApiResponse;
import com.msedcl.main.transactions.dto.AccountResponseDto;
import com.msedcl.main.transactions.dto.BalanceUpdateRequestDto;
import com.msedcl.main.transactions.exception.AccountNotFoundException;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AccountRESTClient {
	private final RestTemplate restTemplate;
	
	public AccountResponseDto getAccountByAccountId(Integer accountId) {
		String url="http://localhost:8081/accountapi/accounts/account/"+accountId;
		try {
		ResponseEntity<ApiResponse<AccountResponseDto>> response= 
				restTemplate.exchange(
							url,
							HttpMethod.GET,
							null,
							//convert JSON into object of ApiResponse<CustomerResponseDto>
							new ParameterizedTypeReference<ApiResponse<AccountResponseDto>>() {			
								});
		return response.getBody().getData();
		} catch (HttpClientErrorException.NotFound ex) {
			throw  new AccountNotFoundException("Invalid Account Id: "+accountId);
		}
	}
	public AccountResponseDto updateBalance(BalanceUpdateRequestDto balanceUpdateRequestDto) {
		String url="http://localhost:8081/accountapi/accounts/account";//+balanceUpdateRequestDto;
		try {
			HttpEntity<BalanceUpdateRequestDto> entity=new HttpEntity<>(balanceUpdateRequestDto);
		ResponseEntity<ApiResponse<AccountResponseDto>> response= 
				restTemplate.exchange(
							url,
							HttpMethod.PUT,
							entity,
							//convert JSON into object of ApiResponse<CustomerResponseDto>
							new ParameterizedTypeReference<ApiResponse<AccountResponseDto>>() {			
								});
		return response.getBody().getData();
		} catch (HttpClientErrorException.NotFound ex) {
			throw  new AccountNotFoundException("Invalid Account Id: "+balanceUpdateRequestDto.getAccountId());
		}
	}
}
	
