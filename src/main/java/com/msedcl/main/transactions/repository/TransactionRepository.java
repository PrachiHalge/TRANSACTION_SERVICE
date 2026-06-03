package com.msedcl.main.transactions.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msedcl.main.transactions.dto.TransactionResponseDto;
import com.msedcl.main.transactions.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Integer>{
public List<Transaction> findByaccountId(int accountid);
}
