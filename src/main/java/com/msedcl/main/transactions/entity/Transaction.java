package com.msedcl.main.transactions.entity;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Transaction_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction extends BaseEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="transaction_id")
	private int transactionId;
	
	@Column(name="account_id")
	private int accountId;
	
	@Column(name="transaction_type")
	private String transactionType;
	
	@Column(name="transaction_date")
	private Date transactionDate;
	
	@Column(name="transaction_amount")
	private double transactionAmount;

}
