package com.smoothai.smoothai.storage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileSystemStorageService implements StorageService {

	private final static Logger log = LoggerFactory.getLogger(FileSystemStorageService.class);

	@Value("${smoothai.image.directory:/upload-dir}")
	private Path saveLocation;

	@Override
	public String store(MultipartFile file) {
		String filename = generateRandomFilename();

		while (isNameAlreadyExisting(filename)) {
			filename = generateRandomFilename();
		}

		try (InputStream inputStream = file.getInputStream()) {
			Files.copy(inputStream, this.saveLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
			return filename;
		} catch (IOException e) {
			log.error("Failed to store file " + filename + ".");
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
			log.info("File Storage initialization started.");
			Files.createDirectories(saveLocation);
		} catch (IOException e) {
			log.error("Could not initialize storage. Storage path: {}.", saveLocation.toString());
			throw new StorageException("Could not initialize storage", e);
		}
	}

	@Override
	public void deleteAll() {
		log.info("Delete all files from {}.", saveLocation.toAbsolutePath());
		FileSystemUtils.deleteRecursively(saveLocation.toFile());
	}

	@Override
	public void delete(String filename) {
		log.info("Delete file with name: {}.", filename);
		saveLocation.resolve(filename).toFile().delete();
	}

	private static String generateRandomFilename() {
		int length = 10;
		boolean useLetters = true;
		boolean useNumbers = false;

		return RandomStringUtils.random(length, useLetters, useNumbers);
	}

	private Boolean isNameAlreadyExisting(String filename) {
		for (File fileEntry : saveLocation.toFile().listFiles()) {
			if (fileEntry.getName().equals(filename)) {
				return true;
			}
		}

		return false;
	}
}
