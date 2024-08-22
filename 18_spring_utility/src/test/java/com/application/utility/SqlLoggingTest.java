package com.application.utility;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.application.utility.chapter01_sqlLogging.SqlLoggingDAO;
import com.application.utility.data.BrandDTO;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SqlLoggingTest {

	@Autowired
	private SqlLoggingDAO sqlLoggingDAO;
	
	@Test
	@DisplayName("브랜드 전체조회 테스트")
	@Order(1)
	public void getBrandList() {
		
		List<BrandDTO> brands = sqlLoggingDAO.getBrandList();
		for (BrandDTO brandDTO : brands) {
			System.out.println(brandDTO);
		}
		
	}
	
	
	@Test
	@DisplayName("브랜드 상세조회 테스트")
	@Order(2)
	public void getBrandDetail() {
		
		long brandId = 1;
		BrandDTO brandDTO = sqlLoggingDAO.getBrandDetail(brandId);
		System.out.println(brandDTO);
		
	}
	
	
	@Test
	@DisplayName("브랜드 추가 테스트")
	@Order(3)
	public void createBrand() {
		BrandDTO brandDTO = new BrandDTO();
		brandDTO.setBrandId(100);
		brandDTO.setBrandNm("추가된브랜드");
		brandDTO.setActiveYn( "Y");
		sqlLoggingDAO.createBrand(brandDTO);
	}
	
	
	@Test
	@DisplayName("브랜드 수정 테스트")
	@Order(4)
	public void updateBrand() {
		BrandDTO brandDTO = new BrandDTO();
		brandDTO.setBrandId(100);
		brandDTO.setBrandNm("추가된브랜드1");
		sqlLoggingDAO.updateBrand(brandDTO);
	}
	
	
	@Test
	@DisplayName("브랜드 삭제 테스트")
	@Order(5)
	public void deleteBrand() {
		long brandId = 100;
		sqlLoggingDAO.deleteBrand(brandId);
	}
	
}
