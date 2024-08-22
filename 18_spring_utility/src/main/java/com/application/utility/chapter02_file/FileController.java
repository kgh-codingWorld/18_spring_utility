package com.application.utility.chapter02_file;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/*

	
	# Spring File 라이브러리 'MultipartFile'
	
	- Spring MVC에서는 MultipartFile객체를 사용하여 클라이언트로부터 전송된 파일을 받아서 처리할 수 있다.


	[ 스프링 파일업로드 기능 구현 방법 ]

		1) 스프링부트 프로젝트에 파일 업로드기능이 포함되어있어서 의존성을 추가하지 않아도 된다.
	
		2) application.properties 파일에 파일업로드 관련 설정을 추가한다.
		
			# 멀티파트 요청에서 개별 파일의 최대 허용 크기 (기본값 1MB)
			spring.servlet.multipart.max-file-size=30MB
			
			# 멀티파트 요청 (파일용량)전체의 최대 허용 크기 (기본값 10MB)         
			spring.servlet.multipart.max-request-size=100MB
			
			# 파일저장 경로 지정 
			file.repo.path=파일저장위치
		
	    3) MultipartFile 객체를 사용하여 파일기능을 구현한다.

*/

@Controller
@RequestMapping("/file")
public class FileController {

	/*
	  
	   import org.springframework.beans.factory.annotation.Value;
	  
	   웹애플리케이션의 설정정보 위치를 통합하기 위하여 
	   application.properties파일에서 파일저장 위치를 명시하고 @Value 애노테이션을 사용하여 대입하여 사용한다. 
	 
	 */
	@Value("${file.repo.path}")
	private String fileRepositoryPath; // C:/spring_file_test/
	
	
	@GetMapping("/main")
	public String main() {
		return "file/fileMain";
	}
	
	
	// 1) 파일 업로드 기초 메뉴얼
	@PostMapping("/upload1")
	@ResponseBody
						  //@RequestParam("name명") 어노테이션을 사용하여 MultipartFile타입으로 파일을 전달받는다. 
	public String upload1(@RequestParam("upFile") MultipartFile upFile) throws IllegalStateException, IOException {
		
		// .isEmpty() > 전송된 파일이 없으면 true를 반환하고 있으면 false를 반환한다.
		if (!upFile.isEmpty()) { // 전송된 파일이 있으면
			
			System.out.println("Uploaded File Name : " + upFile.getOriginalFilename());
			System.out.println("Uploaded File Content Type : " + upFile.getContentType());
			
			File targetFile = new File(fileRepositoryPath + upFile.getOriginalFilename()); // 파일객체를 생성한다.
			upFile.transferTo(targetFile); // transferTo(파일객체) 메서드를 사용하여 파일저장소에 파일을 저장한다.
			
			// 한방에
			// upFile.transferTo(new File(fileRepositoryPath + upFile.getOriginalFilename()));
			
		}
		
		String jsScript = """
				<script>
					alert('업로드 되었습니다.');
					location.href = '/file/main';
				</script>
				""";
		
		return jsScript;
		
	}
	
	
	// 2) 다중파일 업로드 + 파일명 수정(UUID 적용) + 파일업로드 예외처리 메뉴얼
	@PostMapping("/upload2")
	@ResponseBody
					//@RequestParam("name명") 어노테이션을 사용하여 List<MultipartFile>타입으로 파일을 전달받는다. 	
	public String upload2(@RequestParam("files") List<MultipartFile> files) throws IllegalStateException, IOException {
		
		for (MultipartFile file : files) {
			
			if (!file.isEmpty()) { // 전송된 파일이 있으면
				
				// 원본 파일 이름
				String originalFilename = file.getOriginalFilename();
				
				// 범용고유식별자 UUID 생성
				UUID uuid = UUID.randomUUID();
				
				// 확장자 추출
				String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
				
				// 업로드 파일 이름 수정
				String uploadFileName = uuid + extension;
				
				// transferTo(); 메서드를 사용하여 업로드
				file.transferTo(new File(fileRepositoryPath + uploadFileName));
				
			}
			
		}
		
		String jsScript = """
				<script>
					alert('업로드 되었습니다.');
					location.href = '/file/main';
				</script>
				""";
		
		return jsScript;
		
	}
	
	
	// 3) 썸네일기능 구현 메뉴얼
	@GetMapping("/thumbnails")
	@ResponseBody
	public UrlResource thumbnails(@RequestParam("fileName") String fileName) throws MalformedURLException {
		// new UrlResource("file:" + 파일접근경로) 객체를 반환하여 이미지를 조회한다.
		return new UrlResource("file:" + fileRepositoryPath + fileName);  // 이미지 경로를 수정하여 사용한다.
	}
	
	/*
	
		import org.springframework.core.io.InputStreamResource;
		import org.springframework.core.io.Resource;
		import org.springframework.core.io.UrlResource;
		import org.springframework.http.ContentDisposition;
		import org.springframework.http.HttpHeaders;
		import org.springframework.http.HttpStatus;
		import org.springframework.http.ResponseEntity;
		import java.nio.file.Path;
		import java.nio.file.Paths;
		
	 */
	
	// 4) 파일 다운로드 기능 구현 메뉴얼
	@GetMapping("/download")
	public ResponseEntity<Object> download(@RequestParam("fileName") String fileName) {
		
		String downloadFilePath = fileRepositoryPath + fileName;  // 이미지 경로를 수정하여 사용한다.
	       
		try {
			
			Path filePath = Paths.get(downloadFilePath);
			Resource resource = new InputStreamResource(Files.newInputStream(filePath));
			
			File file = new File(downloadFilePath);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentDisposition(ContentDisposition.builder("attachment").filename(file.getName()).build());  
			return new ResponseEntity<Object>(resource, headers, HttpStatus.OK);
			
		} catch(Exception e) {
			return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
		}
		
		
	}
	
	
	// 5) 파일 삭제 메뉴얼
	@PostMapping("/delete")
	@ResponseBody
	public String delete(@RequestParam("deleteFileName") String deleteFileName) {
		
		File deleteFile = new File(fileRepositoryPath + deleteFileName);
		
		if (deleteFile.exists()) { // 파일이 존재하면
			deleteFile.delete(); // 파일 삭제
		}
		
		String jsScript = """
				<script>
					alert('삭제 되었습니다.');
					location.href = '/file/main';
				</script>
				""";
		
		return jsScript;
		
	} 
	
	
	// 6) 파일 수정 메뉴얼
	@PostMapping("/update")
	@ResponseBody
	public String update(@RequestParam("deleteFileName") String deleteFileName , 
						 @RequestParam("modifyFile") MultipartFile modifyFile) throws IllegalStateException, IOException {
		
		
		File deleteFile = new File(fileRepositoryPath + deleteFileName);
		
		// 삭제할 파일이 있고  수정할 파일이 업로드되었으면
		if (deleteFile.exists() && !modifyFile.isEmpty()) {
			
			deleteFile.delete(); // 파일 삭제
			
			// 원본 파일 이름
			String originalFilename = modifyFile.getOriginalFilename();
			
			// 범용고유식별자 UUID 생성
			UUID uuid = UUID.randomUUID();
			
			// 확장자 추출
			String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
			
			// 업로드 파일 이름 수정
			String uploadFileName = uuid + extension;
			
			// transferTo(); 메서드를 사용하여 업로드
			modifyFile.transferTo(new File(fileRepositoryPath + uploadFileName));
			
		}
		
		String jsScript = """
				<script>
					alert('수정 되었습니다.');
					location.href = '/file/main';
				</script>
				""";
		
		return jsScript;
		
		
	}
	
	
}
