package com.example.patient.service;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DataPointStorageService {
	
	@Value("${FILE_DIR}")
	private String fileDir;
	
	public void storeFile(MultipartFile file) throws Exception {
		File outFile = new File(fileDir + "/" +file.getName());
		FileUtils.writeByteArrayToFile(outFile, file.getBytes());
		FileOutputStream fop = new FileOutputStream(outFile);

		// if file doesnt exists, then create it
		if (!outFile.exists()) {
			outFile.createNewFile();
		}


		fop.flush();
		fop.close();
	}

}
