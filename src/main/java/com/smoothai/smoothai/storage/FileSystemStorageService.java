package com.smoothai.smoothai.storage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileSystemStorageService implements StorageService {

	@Value("${smoothai.image.directory:/upload-dir}")
	private Path saveLocation;
	
	@Override
	public void store(MultipartFile file) {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());

		try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, this.saveLocation.resolve(filename),
                StandardCopyOption.REPLACE_EXISTING);
        } catch(IOException e) {
        	throw new StorageException("Failed to store file " + filename, e);
        }
	}

	@Override
	public Path load(String filename) {
		return saveLocation.resolve(filename);
	}

	@Override
    public void init() {
        try {
            Files.createDirectories(saveLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
	
	private static String generateRandomFilename() {
		int length = 10;
	    boolean useLetters = true;
	    boolean useNumbers = false;
	    
	    return RandomStringUtils.random(length, useLetters, useNumbers);
	}
}
