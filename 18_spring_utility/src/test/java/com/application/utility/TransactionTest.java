package com.application.utility;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.application.utility.chapter03_transaction.TransactionService;

@SpringBootTest
public class TransactionTest {

	@Autowired
	private TransactionService transactionService;

	@Test
	@DisplayName("계좌이체 테스트")
	public void bankTransfer(){
		transactionService.bankTransfer();
	}
	
	
	@Test
	@DisplayName("주문 테스트")
	public void addOrder() {
		transactionService.addOrder();
	}
	
}
