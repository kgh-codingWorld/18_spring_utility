package com.application.utility.data;

import java.util.Date;

import lombok.Data;

@Data
public class BrandDTO {

	private long brandId;
	private String brandNm;
	private Date enteredAt;
	private String activeYn;
	
}
