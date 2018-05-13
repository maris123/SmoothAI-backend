package com.smoothai.smoothai;

import static org.springframework.http.HttpStatus.OK;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.smoothai.smoothai.recognizer.PythonScriptRecognizerService;
import com.smoothai.smoothai.storage.StorageService;

@RestController
public class FileUploadController {

	private Logger log = LoggerFactory.getLogger(FileUploadController.class.toString());

	private final StorageService storageService;
	private final SmoothieBook smoothieBook;
	private final PythonScriptRecognizerService scriptService;

	@Autowired
	public FileUploadController(StorageService storageService, SmoothieBook smoothieBook,
			PythonScriptRecognizerService scriptService) {
		this.storageService = storageService;
		this.smoothieBook = smoothieBook;
		this.scriptService = scriptService;
	}

	// see
	// http://codophile.com/2015/05/27/how-to-upload-binary-file-to-spring-rest-service/
	@PostMapping("/recipes")
	public ResponseEntity<Object> getRecipes(@RequestParam("uploadedFile") MultipartFile uploadedFileRef) {
		log.info("Got request on /recipes");

		String filename = storageService.store(uploadedFileRef);
		log.info("Initial file size: {}", new File(storageService.load(filename).toString()).length());
		ImageResizeService.downsize(storageService.load(filename).toString());
		log.info("Second file size: {}", new File(storageService.load(filename + "small").toString()).length());
		Path storedFilePath = storageService.load(filename + "small");
		List<String> ingredients = scriptService.getIngredients(storedFilePath);
		storageService.delete(filename);
		storageService.delete(filename + "small");

		return new ResponseEntity<>(smoothieBook.matching(ingredients), OK);
	}
}
