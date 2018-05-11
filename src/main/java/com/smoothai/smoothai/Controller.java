package com.smoothai.smoothai;

import static org.springframework.http.HttpStatus.OK;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@MultipartConfig(fileSizeThreshold = 20971520)
public class Controller {
	
	// see http://codophile.com/2015/05/27/how-to-upload-binary-file-to-spring-rest-service/
	@PostMapping("/recipes")
	public ResponseEntity<Object> getRecipes(@RequestParam("uploadedFile") MultipartFile uploadedFileRef) {
		return new ResponseEntity<>(null, OK);
	}
}
