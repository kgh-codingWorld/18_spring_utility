package com.application.utility.chapter05_export;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ExportSample {
	
	@GetMapping("/sample")
	public void exportSample(HttpServletResponse response) {
  
		try {
			 
			// Excel 파일명
			String fileName = "엑셀파일다운로드";
			
			// Excel Sheet
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("시트명");
			
			// Excel 헤더
			String[] header = {"컬럼1", "컬럼2", "컬럼3", "컬럼4", "컬럼5"};
			Row row = sheet.createRow(0);                 // 1row를 생성한다.
			for (int i = 0; i < header.length; i++) {
			    Cell cell = row.createCell(i);			  // 1cell을 생성한다.
			    cell.setCellValue(header[i]);			  // 데이터를 대입한다.
			}
	
			// Excel 본문
			for (int i = 1; i < 10; i++) {
				
				row = sheet.createRow(i); 
				
				Cell cell = row.createCell(0);
				cell.setCellValue("컬럼1 데이터");
				
				cell = row.createCell(1);
				cell.setCellValue("컬럼2 데이터");
				
				cell = row.createCell(2);
				cell.setCellValue("컬럼3 데이터");
				
				cell = row.createCell(3);
				cell.setCellValue("컬럼4 데이터");
				
				cell = row.createCell(4);
				cell.setCellValue("컬럼5 데이터");
				
			}

			// 엑셀 파일 생성 및 다운로드
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8") + ".xlsx");
			workbook.write(response.getOutputStream());
			workbook.close();
	
		} catch(IOException e) {
		    e.printStackTrace();
		}
		
	}
	
}
