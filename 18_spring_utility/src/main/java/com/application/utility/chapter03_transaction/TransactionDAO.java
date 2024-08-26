package com.application.utility.chapter03_transaction;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TransactionDAO {
	
	// 예시 1)
	public void transfer(); // 송금
	public void deposit();  // 입금
	
	// 예시 2)
	public void increasePoints();  // 포인트증가
	public void decreaseCartQty(); // 장바구니 수량 삭제
	public void increaseOrerQty(); // 주문 수량 증가
	
}
