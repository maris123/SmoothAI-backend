package com.smoothai.smoothai;

import static org.springframework.http.HttpStatus.OK;

import java.nio.file.Path;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.smoothai.smoothai.storage.StorageService;

@RestController
@MultipartConfig(fileSizeThreshold = 20971520)
public class FileUploadController {
	
	private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }
    
	// see http://codophile.com/2015/05/27/how-to-upload-binary-file-to-spring-rest-service/
	@PostMapping("/recipes")
	public ResponseEntity<Object> getRecipes(@RequestParam("uploadedFile") MultipartFile uploadedFileRef) {
		String filename = storageService.store(uploadedFileRef);
		Path storedFilePath = storageService.load(filename);
		
		storageService.delete(filename);
		//TODO Now returns the absolute path of the stored file. Should be changed to return recipes
		return new ResponseEntity<>(storedFilePath, OK);
	}
}
