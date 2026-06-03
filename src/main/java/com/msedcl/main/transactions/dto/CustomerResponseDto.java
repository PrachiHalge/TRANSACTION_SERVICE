package com.msedcl.main.transactions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDto {
	private int customerId;
	private String Name;
	private String email;
	private String mobileNumber;

}