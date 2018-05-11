package com.smoothai.smoothai.storage;

import java.nio.file.Path;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	void store(MultipartFile file);
	
	Path load(String filename);
	
	void init();
}
