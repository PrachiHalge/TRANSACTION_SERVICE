package com.msedcl.main.transactions.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix="transactions")
public class TransactionContactsDTO {
	private String message;
	private Map<String,String> contactDetails;
	private List<String> onCallSupport;

}
