package com.smoothai.smoothai.storage;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	String store(MultipartFile file);
	
	Path load(String filename);
	
	void deleteAll();
	
	void delete(String filename);
	
	void init();
}
