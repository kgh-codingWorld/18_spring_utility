package com.application.utility.data;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ProductDTO {

	private long productId;
	private String productNm;
	private int price;
	private int deliveryPrice;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date enrollAt;
	private long brandId;
	
}
