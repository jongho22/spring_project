package com.office.library.admin.utill;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileService {
	public String upload(MultipartFile file) {
		
		boolean result = false;
		
		// 파일 저장
		String fileOriName = file.getOriginalFilename();
		String fileExtension = fileOriName.substring(fileOriName.lastIndexOf("."), fileOriName.length());
		String uploadDir = "/Users/shinjongho/Desktop/신종호/개인프로젝트/spring_project/";
		
		UUID uuid = UUID.randomUUID();
		String uniqueName = uuid.toString().replace("-", "");
		
		File saveFile = new File(uploadDir + "/" + uniqueName + fileExtension);
		
		if (!saveFile.exists()) saveFile.mkdir();
		
		try {
			file.transferTo(saveFile);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (result) {
			System.out.println("[UploadFileService] FILE UPLOAD SUCCESS!!");
			return uniqueName + fileExtension;
		} else {
			System.out.println("[UploadFileService] FILE UPLOAD FAIL!!");
			return null;
		}
	}
}
